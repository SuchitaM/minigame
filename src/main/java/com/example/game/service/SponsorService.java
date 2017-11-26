package com.example.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.game.dao.SponsorRepository;
import com.example.game.exception.ParamNotFoundException;
import com.example.game.model.Address;
import com.example.game.model.Player;
import com.example.game.model.Sponsor;

@Service
public class SponsorService {
	
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	public ResponseEntity<?> createSponsor(String name,String description,String street,String city, String state,String zip
			) {
		
		Sponsor sponsor = new Sponsor(name, description,new Address(street,city,state,zip));
		
		if(name==""){
			throw new ParamNotFoundException();
		}
		try
		{
		sponsorRepository.save(sponsor);
		System.out.println("player added");
		
		} catch(Exception e){
		// TODO Auto-generated method stub
		return  new ResponseEntity<>("400-Paramter Exception", 
				HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(sponsor, 
				HttpStatus.CREATED);
	
}
	
	
	
	public ResponseEntity<?> getSponsor(Long id){
		
		System.out.println("inside getPlayer()");
		Sponsor sponsor = sponsorRepository.findOne(id);
		if(sponsor == null){
			return new ResponseEntity<>("404-Sponsor Id not Found",HttpStatus.NOT_FOUND);
		}
		if(sponsor!=null){
		System.out.println("inside getPlayer() if");
		 try{
		
		
		}
		catch(Exception e){
			return new 	ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
		}
		}
		 return new ResponseEntity<>(sponsor,HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<?> updateSponsor(Long id, String name,
			String description,String street, String city, String state, String zip) {
				
		Sponsor sponsor = sponsorRepository.findOne(id);
		if(sponsor == null){
			return new ResponseEntity<>("404-Sponsor Id not Found",HttpStatus.NOT_FOUND);
		}
		System.out.println(sponsor.getId());
		if(sponsor!=null){
			try{
				
				sponsor.setName(name);
				sponsor.setDescription(description);
				Address a = new Address(street,city,state,zip);
				sponsor.setAddress(a);
				//sponsor.setAddress(address);
				//sponsor
			//	sponsor
				
			}
			catch(Exception e){
				return new ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
			}
		}
		
		return getSponsor(id);
	}
	
	
public ResponseEntity<?> deleteSponsor(Long id){
		
		System.out.println("inside deleteSponsor()");
		Sponsor sponsor = sponsorRepository.findOne(id);
		if(sponsor == null){
			return new ResponseEntity<>("404-Sponsor Id not Found",HttpStatus.NOT_FOUND);
		}
		if(sponsor!=null){
		System.out.println("inside deletesponsor() if");
		try{
		   sponsorRepository.delete(sponsor);
		
		}
		catch(Exception e){
			return new 	ResponseEntity<>("400-Parameter Exception",HttpStatus.NOT_FOUND);
		}
		
		}
		return new ResponseEntity<>(sponsor,HttpStatus.OK);
	}	

}
