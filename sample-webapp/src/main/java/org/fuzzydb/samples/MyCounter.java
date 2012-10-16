package org.fuzzydb.samples;

import java.io.Serializable;

import org.fuzzydb.core.annotations.Key;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

/**
 * An example of a serializable POJO which is passed straight through Spring Data
 * so must (currently) use fuzzydb annotation {@link Key} for index declaration.
 *
 * Verson 1.1 of fuzzydb-spring will have support for {@link Id} for storing into
 * {@link CrudRepository}.
 */
public class MyCounter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Key(unique=true)
	private final String page;

	private int count = 0;

	public MyCounter(String page) {
		this.page = page;
	}

	public void increment() {
		count++;
	}

	public int getCount() {
		return count;
	}
}
