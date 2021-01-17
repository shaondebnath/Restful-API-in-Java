package de.tub.ise.anwsys.response;

import java.sql.Timestamp;

public class ThrowResponse {
	
	public void throwGeneralResponse() throws Exception{
		Exception e = new Exception("Error from general Response");
		throw e;
	}
	
	public void throwCustomResponse(int errorCode, String msg) throws CustomException{
		CustomException ce = new CustomException();
		ce.setCode(errorCode);
		ce.setMessage(msg);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		ce.setTimestamp(timestamp);
		
		throw ce;
	}

}
