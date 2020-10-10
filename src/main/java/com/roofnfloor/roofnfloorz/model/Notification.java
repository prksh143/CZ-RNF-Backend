package com.roofnfloor.roofnfloorz.model;

public class Notification {

    private String message;
    private Object response;

    public Notification (String content) {
        this.message = content;
    }

    public String getContent() {
        return message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}