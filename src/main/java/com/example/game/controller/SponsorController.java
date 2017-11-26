package com.example.game.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.game.service.SponsorService;

@RestController
@Transactional
public class SponsorController {

	@Autowired
	private SponsorService sponsorService;
	
	
	
	@RequestMapping(value = "/sponsor", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createSponsor(
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="street",required=false) String street,
			@RequestParam(value="city",required=false) String city,
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="zip",required=false) String zip)
	{
		System.out.println("add sponsor");
		return sponsorService.createSponsor(name,description,street,city,state,zip);
	}
	
	
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSponsor(
			@PathVariable("id") Long id) 
			{
		
		System.out.println("sponsor milala");
		return sponsorService.getSponsor(id);
			
	}
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePlayer(@PathVariable("id") Long id,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("street") String street,
			@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("zip") String zip){
		
		System.out.println("in update");
		return sponsorService.updateSponsor(id,name,description,street,city,state,zip);
	}
	
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id){
		
		System.out.println("in player delete");
		return sponsorService.deleteSponsor(id);
	}
	
	
}
