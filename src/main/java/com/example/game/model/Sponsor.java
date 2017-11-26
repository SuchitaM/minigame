package com.example.game.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sponsor")
public class Sponsor {
		
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   @Column(name="sponsor_id")
	   private long id;
	   private String name;
	   private String description;
	   @Embedded
	   private Address address;
	   
	   
	public Sponsor( String name, String description, Address address) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
	}
	
	
	public Sponsor(){
	
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	   
	   
	   
	   
	
}
