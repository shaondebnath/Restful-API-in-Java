package de.tub.ise.anwsys.response;


import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseHandlingController {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseMessage>customException(CustomException e){
		ResponseMessage responseMessage = new ResponseMessage();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		responseMessage.setTimeStamp(timestamp);
		responseMessage.setCode(e.getCode());
		responseMessage.setMessage(e.getMessage());
		
		
		return new ResponseEntity<ResponseMessage>(responseMessage,e.getMyStatus());
		
	}


}
