package com.example.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.game.dao.PlayerRepository;
import com.example.game.dao.SponsorRepository;
import com.example.game.exception.ParamNotFoundException;
import com.example.game.model.Address;
import com.example.game.model.Player;
import com.example.game.model.Sponsor;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private SponsorRepository sponsorRepository;
	
	/*
	public Player createPlayer(Player player){
		return playerRepository.save(player);
	}
*/
	public ResponseEntity<?> createPlayer(String firstname, String lastname, String email,String description,String street,String city, String state,String zip,Long sponsor_id
			) {
			
		Sponsor sp=null;
		if(sponsor_id!=null){
			 sp = sponsorRepository.findOne(sponsor_id);
			
		}
		
		Player player = new Player(firstname,lastname, email, description,new Address(street,city,state,zip),sp);
		
		if(firstname=="" || lastname=="" || email==""){
			throw new ParamNotFoundException();
		}
		
		try{
		
			playerRepository.save(player);
			System.out.println("player added");
		
		} catch(Exception e){
		return  new ResponseEntity<>("BadRequest", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(player, 
				HttpStatus.CREATED);
	
}
	

	

	
	
	public ResponseEntity<?> getPlayer(Long id){
		
		System.out.println("inside getPlayer()");
		Player player = playerRepository.findOne(id);
		if (player==null){
			return new ResponseEntity<>("404-Player Id not Found",HttpStatus.NOT_FOUND);
		}
		if(player!=null){
			System.out.println("inside getPlayer() if");
			try{
			
			}
			catch(Exception e){
				return new 	ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(player,HttpStatus.OK);
		
		
		
	}
	
	public ResponseEntity<?> updatePlayer(Long id, String firstname, String lastname, String email,
			String description,String street,String city, String state,String zip,Long sponsor_id) {
				
		Player player = playerRepository.findOne(id);
		if (player==null){
			return new ResponseEntity<>("404-Player Id not Found",HttpStatus.NOT_FOUND);
		}
		System.out.println(player.getId());
		if(player!=null){
			
				if(firstname=="" || lastname=="" || email=="" || description== "" || street =="" || city=="" || state=="" || zip=="" ){
						throw new ParamNotFoundException();
					}
						try{
							player.setDescription(description);
							player.setEmail(email);
							player.setFirstname(firstname);
							player.setLastname(lastname);
							Address a = new Address(street,city,state,zip);
							player.setAddress(a);
							Sponsor sp = sponsorRepository.findOne(sponsor_id);
				
							player.setSponsor(sp);
							}
							catch(Exception e){
				
									return new ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
							}
				
		}
		
		
		return getPlayer(id);
	}
	
public ResponseEntity<?> deletePlayer(Long id){
		
		System.out.println("inside deletePlayer()");
		Player player = playerRepository.findOne(id);
		if (player==null){
			return new ResponseEntity<>("404-Player Id not Found",HttpStatus.NOT_FOUND);
		}
		if(player!=null){
			try{
					System.out.println("inside deletePlayer() if");
					playerRepository.delete(player);
					
		
				}
				catch(Exception e){
				return new 	ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
				}
		}
		return new ResponseEntity<>(player,HttpStatus.OK);
		
	}

public ResponseEntity<?> updateOpponents(Long id1, Long id2) {
			
	Player player1 = playerRepository.findOne(id1);
	System.out.println(player1.getId());
	
	Player player2 = playerRepository.findOne(id2);
	System.out.println(player2.getId());
	if (player1==null || player2==null){
		return new ResponseEntity<>("404- Player Id not Found",HttpStatus.NOT_FOUND);
	}
	if(player1!=null && player2!=null){
		
		
		try{
			 List<Player> p1 = player1.getOpponents();
			 List<Player> p2 = player2.getOpponents();
			 if(!p1.contains(player2)){
				 p1.add(player2);
				 player1.setOpponents(p1);
			 }
			 if(!p2.contains(player1)){
				 p2.add(player1);
				 player2.setOpponents(p2);
			 }

		}
		catch(Exception e){
			
			return new ResponseEntity<>("400-Paramter Exception",HttpStatus.NOT_FOUND);
		}
	}
	
	return new ResponseEntity<>("200-Created",HttpStatus.OK);
		
		
	}

public ResponseEntity<?> deleteOpponents(Long id1, Long id2) {
	
	Player player1 = playerRepository.findOne(id1);
	System.out.println(player1.getId());
	
	Player player2 = playerRepository.findOne(id2);
	System.out.println(player2.getId());
	if (player1==null || player2==null){
		return new ResponseEntity<>("404- Player Id not Found",HttpStatus.NOT_FOUND);
	}
	if(player1!=null && player2!=null){
		
		
		try{
			List<Player> p1 = player1.getOpponents();
			 List<Player> p2 = player2.getOpponents();
			 if(p1.contains(player2)){
				 
				 p1.remove(player2);
				 player1.setOpponents(p1);
				 
			 }
			 if(p2.contains(player1)){
				 p2.remove(player1);
				 player2.setOpponents(p2);
			 }

		}
		catch(Exception e){
			
			return new ResponseEntity<>("400-Paramter Exception",HttpStatus.NOT_FOUND);
		}
	}
	
	return new ResponseEntity<>("200-Created",HttpStatus.OK);
		
		
	}
	
	
}
