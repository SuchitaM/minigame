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
import com.example.game.service.PlayerService;

@RestController
@Transactional
//@RequestMapping(value="/api/players")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value = "/player", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPlayer(
			@RequestParam(value="firstname",required=true) String firstname,
			@RequestParam(value="lastname",required=true) String lastname,
			@RequestParam(value="email",required=true) String email,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="street",required=false) String street,
			@RequestParam(value="city",required=false) String city,
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="zip",required=false) String zip,
			@RequestParam(value="sponsor_id",required=false) Long sponsor_id)
	{
		System.out.println("add player");
		return playerService.createPlayer(firstname,lastname,email,description,street,city,state,zip,sponsor_id);
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPlayer(
			@PathVariable("id") Long id) 
			{
		
		
		return playerService.getPlayer(id);
			
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePlayer(
			@PathVariable("id") Long id,
			@RequestParam(value="firstname",required=true) String firstname,
			@RequestParam(value="lastname",required=true) String lastname,
			@RequestParam(value="email",required=true) String email,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="street",required=false) String street,
			@RequestParam(value="city",required=false) String city,
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="zip",required=false) String zip,
			@RequestParam(value="sponsor_id",required=false) Long sponsor_id
			){
		
		System.out.println("in update");
		return playerService.updatePlayer(id,firstname,lastname,email,description,street,city,state,zip,sponsor_id);
	}
	
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePlayer(@PathVariable("id") Long id){
		
		System.out.println("in player delete");
		return playerService.deletePlayer(id);
	}

	@RequestMapping(value = "/opponents/{id1}/{id2}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateOpponents(
			@PathVariable("id1") Long id1,
			@PathVariable("id2") Long id2
			
			){
		
		System.out.println("in update");
		return playerService.updateOpponents(id1,id2);
	}
	
	@RequestMapping(value = "/opponents/{id1}/{id2}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteOpponents(
			@PathVariable("id1") Long id1,
			@PathVariable("id2") Long id2
			
			){
		
		System.out.println("in update");
		return playerService.deleteOpponents(id1,id2);
	}
	
	
}
