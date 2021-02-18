package com.backend.api.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
