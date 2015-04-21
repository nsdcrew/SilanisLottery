package com.silanis.lottery.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




//--- Entities
import com.silanis.lottery.bean.Ticket;
import com.silanis.lottery.bean.Draw;
import com.silanis.lottery.test.TicketFactoryForTest;
import com.silanis.lottery.test.DrawFactoryForTest;

//--- Services 
import com.silanis.lottery.business.service.TicketService;
import com.silanis.lottery.business.service.DrawService;

//--- List Items 
import com.silanis.lottery.web.listitem.DrawListItem;
import com.silanis.lottery.web.common.Message;
import com.silanis.lottery.web.common.MessageHelper;
import com.silanis.lottery.web.common.MessageType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath:/WEB-INF/spring-webmvc.xml"})
public class TicketControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@InjectMocks
	private TicketController ticketController;
	@Mock
	private TicketService ticketService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private DrawService drawService; // Injected by Spring

	private TicketFactoryForTest ticketFactoryForTest = new TicketFactoryForTest();
	private DrawFactoryForTest drawFactoryForTest = new DrawFactoryForTest();

	List<Draw> draws = new ArrayList<Draw>();

	private void givenPopulateModel() {
		Draw draw1 = drawFactoryForTest.newDraw();
		Draw draw2 = drawFactoryForTest.newDraw();
		List<Draw> draws = new ArrayList<Draw>();
		draws.add(draw1);
		draws.add(draw2);
		when(drawService.findAll()).thenReturn(draws);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Ticket> list = new ArrayList<Ticket>();
		when(ticketService.findAll()).thenReturn(list);
		
		// When
		String viewName = ticketController.list(model);
		
		// Then
		assertEquals("ticket/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
//	@Test
//	public void formForCreate() {
//		// Given
//		Model model = new ExtendedModelMap();
//		
//		givenPopulateModel();
//		
//		// When
//		Draw draw = drawFactoryForTest.newDraw();
//		Integer idDraw = draw.getIdDraw();
//		when(drawService.findById(idDraw)).thenReturn(draw);
//		String viewName = ticketController.formForCreate(model,idDraw);
//		
//		// Then
//		assertEquals("ticket/form", viewName);
//		
//		Map<String,?> modelMap = model.asMap();
//		
//		assertNull(((Ticket)modelMap.get("ticket")).getIdTicket());
//		assertEquals("create", modelMap.get("mode"));
//		assertEquals("/ticket/create", modelMap.get("saveAction"));
//		
//		@SuppressWarnings("unchecked")
//		List<DrawListItem> drawListItems = (List<DrawListItem>) modelMap.get("listOfDrawItems");
//		assertEquals(2, drawListItems.size());
//		
//	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		Integer idTicket = ticket.getIdTicket();
		when(ticketService.findById(idTicket)).thenReturn(ticket);
		
		// When
		String viewName = ticketController.formForUpdate(model, idTicket);
		
		// Then
		assertEquals("ticket/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticket, (Ticket) modelMap.get("ticket"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/ticket/update", modelMap.get("saveAction"));
		
		List<DrawListItem> drawListItems = (List<DrawListItem>) modelMap.get("listOfDrawItems");
		assertEquals(2, drawListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Ticket ticketCreated = new Ticket();
		when(ticketService.create(ticket)).thenReturn(ticketCreated); 
		
		// When
		String viewName = ticketController.create(ticket, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ticket/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticketCreated, (Ticket) modelMap.get("ticket"));
		assertEquals("details", modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = ticketController.create(ticket, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ticket/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticket, (Ticket) modelMap.get("ticket"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/ticket/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DrawListItem> drawListItems = (List<DrawListItem>) modelMap.get("listOfDrawItems");
		assertEquals(2, drawListItems.size());
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		Ticket ticket = ticketFactoryForTest.newTicket();
		
		Exception exception = new RuntimeException("test exception");
		when(ticketService.create(ticket)).thenThrow(exception);
		
		// When
		String viewName = ticketController.create(ticket, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ticket/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticket, (Ticket) modelMap.get("ticket"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/ticket/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "ticket.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<DrawListItem> drawListItems = (List<DrawListItem>) modelMap.get("listOfDrawItems");
		assertEquals(2, drawListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		Integer idTicket = ticket.getIdTicket();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Ticket ticketSaved = new Ticket();
		ticketSaved.setIdTicket(idTicket);
		when(ticketService.update(ticket)).thenReturn(ticketSaved); 
		
		// When
		String viewName = ticketController.update(ticket, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/ticket/form/"+ticket.getIdTicket(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticketSaved, (Ticket) modelMap.get("ticket"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = ticketController.update(ticket, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ticket/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticket, (Ticket) modelMap.get("ticket"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/ticket/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DrawListItem> drawListItems = (List<DrawListItem>) modelMap.get("listOfDrawItems");
		assertEquals(2, drawListItems.size());
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		Ticket ticket = ticketFactoryForTest.newTicket();
		
		Exception exception = new RuntimeException("test exception");
		when(ticketService.update(ticket)).thenThrow(exception);
		
		// When
		String viewName = ticketController.update(ticket, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ticket/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ticket, (Ticket) modelMap.get("ticket"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/ticket/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "ticket.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<DrawListItem> drawListItems = (List<DrawListItem>) modelMap.get("listOfDrawItems");
		assertEquals(2, drawListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		Integer idTicket = ticket.getIdTicket();
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();
		// When
		String viewName = ticketController.delete(redirectAttributes, idTicket, idDraw);
		
		// Then
		verify(ticketService).delete(idTicket);
		assertEquals("redirect:/draw/details/"+idDraw.toString(), viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		Integer idTicket = ticket.getIdTicket();
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(ticketService).delete(idTicket);
		
		// When
		String viewName = ticketController.delete(redirectAttributes, idTicket, idDraw);
		
		// Then
		verify(ticketService).delete(idTicket);
		assertEquals("redirect:/draw/details/"+idDraw.toString(), viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "ticket.error.delete", exception);
	}
	
	
}
