package com.jsp.eventmanagement.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.* ;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jsp.eventmanagement.Response.ResponseStructure;
import com.jsp.eventmanagement.model.Event;
import com.jsp.eventmanagement.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private MessageSource messageSource ;
	
	@Autowired
	private EventService service ;
	
	@ApiResponses(value = {
		    @ApiResponse(description = "Event saved successfully", responseCode = "201", 
		        content = {
		        	@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseStructure.class)),
		            @Content(mediaType = "application/xml", schema = @Schema(implementation = ResponseStructure.class))
		        }),
		    @ApiResponse(description = "Invalid input or missing attributes", responseCode = "400", 
		        content = {
		            @Content(mediaType = "application/json" ),
		            @Content(mediaType = "application/xml" )
		        }),
		    @ApiResponse(description = "Unable to save the event due to internal server error", responseCode = "500", 
		        content = {
		            @Content(mediaType = "application/json"),
		            @Content(mediaType = "application/xml")
		        })
		})
		@Operation(description = "To save the Event")
		@PostMapping("event")
		public ResponseEntity<ResponseStructure<Event>> saveEvent(@RequestBody @Valid Event event) {
		
			System.out.println(event);
		    return service.saveEvent(event);
		}

	
	@GetMapping("event/{id}")
	public EntityModel<Event> findEventById(@PathVariable int id) {
		
		ResponseEntity<ResponseStructure<Event>> foundEntity = service.findEventById(id) ;
		
		EntityModel<Event> entity = EntityModel.of(foundEntity.getBody().getData()) ;
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllEvents()) ;
		
		entity.add(link.withRel("all-events")) ;
		
	    return entity ;
	}
	
	@PutMapping("event")
	public ResponseEntity<ResponseStructure<Event>> updateEvent(@RequestBody Event event) {
		return service.updateEvent(event);
	}
	
	@DeleteMapping("event/{id}")
	public ResponseEntity<ResponseStructure<Boolean>> deleteEventById(@PathVariable int id) {
		
		return service.DeleteEventById(id) ;
	}
	
	@GetMapping("event/wishes")
	public String getMessage() {
		
		Locale locale = LocaleContextHolder.getLocale() ;
		
		return messageSource.getMessage("good.morning.message", null, "Default Message",locale ) ;
	}
	
	@GetMapping("/events")
	public List<Event> getAllEvents(){
		
		return service.getAllEvents() ;
	}
}
