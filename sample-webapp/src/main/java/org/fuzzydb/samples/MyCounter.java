package org.fuzzydb.samples;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.wwm.db.Ref;

public class MyCounter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	@Id
	private Ref<MyCounter> ref;

	public int count = 0;
}
