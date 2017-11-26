package com.example.game.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="400-No such parameter")
public class ParamNotFoundException extends RuntimeException {
	
	public ParamNotFoundException(){
		//super(message);
		
	}

}
