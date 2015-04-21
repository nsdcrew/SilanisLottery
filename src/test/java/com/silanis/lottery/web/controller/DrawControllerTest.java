package com.silanis.lottery.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


//--- Entities
import com.silanis.lottery.bean.Draw;
import com.silanis.lottery.test.DrawFactoryForTest;

//--- Services 
import com.silanis.lottery.business.service.DrawService;
import com.silanis.lottery.web.common.Message;
import com.silanis.lottery.web.common.MessageHelper;
import com.silanis.lottery.web.common.MessageType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class DrawControllerTest {
	
	@InjectMocks
	private DrawController drawController;
	@Mock
	private DrawService drawService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private DrawFactoryForTest drawFactoryForTest = new DrawFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Draw> list = new ArrayList<Draw>();
		when(drawService.findAll()).thenReturn(list);
		
		// When
		String viewName = drawController.list(model);
		
		// Then
		assertEquals("draw/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() throws ParseException {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = drawController.formForCreate(model);
		
		// Then
		assertEquals("draw/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Draw)modelMap.get("draw")).getIdDraw());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/draw/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();
		when(drawService.findById(idDraw)).thenReturn(draw);
		
		// When
		String viewName = drawController.formForUpdate(model, idDraw);
		
		// Then
		assertEquals("draw/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(draw, (Draw) modelMap.get("draw"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/draw/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForDetails() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();
		when(drawService.findById(idDraw)).thenReturn(draw);
		
		// When
		String viewName = drawController.formForDetails(model, idDraw);
		
		// Then
		assertEquals("draw/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(draw, (Draw) modelMap.get("draw"));
		assertEquals("details", modelMap.get("mode"));
		assertEquals("/draw/launch", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Draw draw = drawFactoryForTest.newDraw();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Draw drawCreated = new Draw();
		when(drawService.create(draw)).thenReturn(drawCreated); 
		
		// When
		String viewName = drawController.create(draw, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/draw/list", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(drawCreated, (Draw) modelMap.get("draw"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Draw draw = drawFactoryForTest.newDraw();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = drawController.create(draw, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("draw/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(draw, (Draw) modelMap.get("draw"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/draw/create", modelMap.get("saveAction"));
		
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

		Draw draw = drawFactoryForTest.newDraw();
		
		Exception exception = new RuntimeException("test exception");
		when(drawService.create(draw)).thenThrow(exception);
		
		// When
		String viewName = drawController.create(draw, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("draw/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(draw, (Draw) modelMap.get("draw"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/draw/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "draw.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Draw drawSaved = new Draw();
		drawSaved.setIdDraw(idDraw);
		when(drawService.update(draw)).thenReturn(drawSaved); 
		
		// When
		String viewName = drawController.update(draw, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/draw/list", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(drawSaved, (Draw) modelMap.get("draw"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Draw draw = drawFactoryForTest.newDraw();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = drawController.update(draw, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("draw/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(draw, (Draw) modelMap.get("draw"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/draw/update", modelMap.get("saveAction"));
		
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

		Draw draw = drawFactoryForTest.newDraw();
		
		Exception exception = new RuntimeException("test exception");
		when(drawService.update(draw)).thenThrow(exception);
		
		// When
		String viewName = drawController.update(draw, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("draw/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(draw, (Draw) modelMap.get("draw"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/draw/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "draw.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();
		
		// When
		String viewName = drawController.delete(redirectAttributes, idDraw);
		
		// Then
		verify(drawService).delete(idDraw);
		assertEquals("redirect:/draw/list", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Draw draw = drawFactoryForTest.newDraw();
		Integer idDraw = draw.getIdDraw();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(drawService).delete(idDraw);
		
		// When
		String viewName = drawController.delete(redirectAttributes, idDraw);
		
		// Then
		verify(drawService).delete(idDraw);
		assertEquals("redirect:/draw/list", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "draw.error.delete", exception);
	}
	
	
}
