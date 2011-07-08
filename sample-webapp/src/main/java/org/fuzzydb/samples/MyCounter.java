package org.fuzzydb.samples;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.wwm.db.GenericRef;

public class MyCounter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private GenericRef<MyCounter> ref;

	public int count = 0;
}
