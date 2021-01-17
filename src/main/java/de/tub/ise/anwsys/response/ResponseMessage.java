package de.tub.ise.anwsys.response;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
	
	private Timestamp timestamp;
	private int code;
	private String message;
	
	
	
	public ResponseMessage() {
		
	}
	
	public ResponseMessage(Timestamp timestamp, int code, String message) {
		super();
		this.timestamp=timestamp;
		this.code = code;
		this.message= message;		
	}

	public Timestamp getTimeStamp() {
		return timestamp;
	}

	public void setTimeStamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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




}
