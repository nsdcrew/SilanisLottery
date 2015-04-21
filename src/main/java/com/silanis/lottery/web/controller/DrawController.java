/*
 * Created on 9 avr. 2015 ( Time 11:50:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;






//--- Common classes
import com.silanis.lottery.web.common.AbstractController;
import com.silanis.lottery.web.common.FormMode;
import com.silanis.lottery.web.common.Message;
import com.silanis.lottery.web.common.MessageType;

//--- Entities
import com.silanis.lottery.bean.Draw;

//--- Services 
import com.silanis.lottery.business.service.DrawService;


/**
 * Spring MVC controller for 'Draw' management.
 */
@Controller
@RequestMapping("/draw")
public class DrawController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "draw";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "draw/form";
	private static final String JSP_LIST   = "draw/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/draw/create";
	private static final String SAVE_ACTION_UPDATE   = "/draw/update";
	private static final String SAVE_ACTION_LAUNCH   = "/draw/launch";
	//private static final String SAVE_ACTION_DETAILS   = "/draw/details";
	
	private Integer nbParticipant ;

	//--- Main entity service
	@Resource
    private DrawService drawService; // Injected by Spring
	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public DrawController() {
		super(DrawController.class, MAIN_ENTITY_NAME );
		log("DrawController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param draw
	 */
	private void populateModel(Model model, Draw draw, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, draw);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
		}
		else if ( formMode == FormMode.DETAILS ) {
			model.addAttribute(MODE, MODE_DETAILS); // The form is in "details" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_LAUNCH); 	
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Draw found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list") // GET
	public String list(Model model) {
		log("Action 'list'");
		List<Draw> list = drawService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Draw
	 * @param model Spring MVC model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/create") // GET
	public String formForCreate(Model model) throws ParseException {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		Draw draw = new Draw();	
		draw.setIsOpen(true);
		
		draw.setCreationDate(new Date());
		populateModel( model, draw, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Draw
	 * @param model Spring MVC model
	 * @param idDraw  primary key element
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/update/{idDraw}") // GET
	public String formForUpdate(Model model, @PathVariable("idDraw") Integer idDraw ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		Draw draw = drawService.findById(idDraw);
		populateModel( model, draw, FormMode.UPDATE);		
		return JSP_FORM;
	}
	
	/**
	 * Shows a form page in order to see details of an existing Draw
	 * @param model Spring MVC model
	 * @param idDraw  primary key element
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/details/{idDraw}") // GET
	public String formForDetails(Model model, @PathVariable("idDraw") Integer idDraw ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		Draw draw = drawService.findByIdIncludingParticipantAndWinners(idDraw);
		nbParticipant = draw.getListTickets().size();
		populateModel( model, draw, FormMode.DETAILS);		
		return JSP_FORM;
	}

	
	/**
	 * 'LAUNCH' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param draw  entity to be launched
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value =  "/launch" ) // POST
	public String launch(Draw draw, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				
				if(nbParticipant>=3)
				{
					drawService.launchById(draw.getIdDraw()); 
					messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"draw.lunched"));
				}
				else
				{
					messageHelper.addException(model, "Insuffisant.ticketNumber", new Exception());
				}

				Draw drawLunched = drawService.findByIdIncludingParticipantAndWinners(draw.getIdDraw());
				populateModel( model, drawLunched, FormMode.DETAILS);
				return JSP_FORM;
				
				
				
			} else {
				populateModel( model, draw, FormMode.DETAILS);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "draw.error.launched", e);
			populateModel( model, draw, FormMode.DETAILS);
			return JSP_FORM;
		}
	}
	
	
	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param draw  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value =  "/create" ) // POST
	public String create(@Valid Draw draw, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				draw.setIsOpen(true);
				draw.setCreationDate(new Date());
				Draw drawCreated = drawService.create(draw); 
				model.addAttribute(MAIN_ENTITY_NAME, drawCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToList();
	//			return "/" + super.entityName + "/list";
			} else {
				populateModel( model, draw, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "draw.error.create", e);
			populateModel( model, draw, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param draw  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value =  "/update" ) //  POST
	public String update(@Valid Draw draw, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				Draw drawSaved = drawService.update(draw);
				model.addAttribute(MAIN_ENTITY_NAME, drawSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				//return redirectToForm(httpServletRequest, draw.getIdDraw());
				return redirectToList();
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, draw, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "draw.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, draw, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param idDraw  primary key element
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/delete/{idDraw}") // GET 
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("idDraw") Integer idDraw) {
		log("Action 'delete'" );
		try {
			drawService.delete( idDraw );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "draw.error.delete", e);
		}
		return redirectToList();
	}

}
