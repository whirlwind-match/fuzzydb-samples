package org.fuzzydb.samples;

import java.io.Serializable;

import org.springframework.data.annotation.Persistent;

// TODO: make @Persistent a target for an aspect to mixin Serializable and/or 
// Ref, RefAware interfaces and implementations to save developer from these concerns :)
@Persistent
public class MyCounter implements Serializable {
	private static final long serialVersionUID = 1L;

	public int count = 0;
}
