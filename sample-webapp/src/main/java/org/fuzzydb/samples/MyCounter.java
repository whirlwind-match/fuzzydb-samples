package org.fuzzydb.samples;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class MyCounter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	@Id
	private String page;
	
	public int count = 0;

	public MyCounter(String page) {
		this.page = page;
	}

}
