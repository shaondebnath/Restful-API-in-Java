package de.tub.ise.anwsys.response;
import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
//public class CustomException extends Exception{
public class CustomException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private int code;
	private String message;
	private Timestamp timestamp;
	private HttpStatus myStatus;
	
	public CustomException() {
		
	}
	public CustomException(int code, String msg, HttpStatus myStatus) {
		this.code = code;
		this.message= msg;
		Timestamp currenttimestamp = new Timestamp(System.currentTimeMillis());
		this.timestamp = currenttimestamp;
		this.myStatus = myStatus;
		
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getMyStatus() {
		return myStatus;
	}
	public void setMyStatus(HttpStatus myStatus) {
		this.myStatus = myStatus;
	}
	
	
	

}
