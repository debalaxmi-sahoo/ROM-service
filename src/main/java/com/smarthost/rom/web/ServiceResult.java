package com.smarthost.rom.web;

import java.util.ArrayList;
import java.util.List;

public class ServiceResult<T> {
	
	private Status status;
	
	private T result;
	
	private List<String> errors = new ArrayList<>();
	
	public enum Status {SUCCESS, FAILURE}

	public ServiceResult() {
		
	}
	
	public ServiceResult(T result) {
		this.result = result;
		this.status = Status.SUCCESS;
	}
	
	public ServiceResult(T result, List<String> errors) {
		this.result = result;
		this.errors = errors;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
