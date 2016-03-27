package org.livecloud.ylog.entity;

import java.io.Serializable;

public class Options implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5061104505523594100L;
	private long id;
	private String name;
	private String value;
	private String autoload;

	public Options() {}
	
	public Options(String name, String value) {
		this.name = name;
		this.value = value;
		this.autoload = "yes";
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getAutoload() {
		return autoload;
	}

	public void setAutoload(String autoload) {
		this.autoload = autoload;
	}
	

	public String toString() {
		return "Option:" + name + " " + value;
	}
}