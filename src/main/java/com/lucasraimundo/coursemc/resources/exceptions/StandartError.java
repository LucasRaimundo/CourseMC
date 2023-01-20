package com.lucasraimundo.coursemc.resources.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long timetamp;
	private Integer status;
	private String error;
	private String msg;
	private String path;
	
	public StandartError(Long timetamp, Integer status, String error, String msg, String path) {
		super();
		this.timetamp = timetamp;
		this.status = status;
		this.error = error;
		this.msg = msg;
		this.path = path;
	}

	public Long getTimetamp() {
		return timetamp;
	}

	public void setTimetamp(Long timetamp) {
		this.timetamp = timetamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
	

	
	
}
