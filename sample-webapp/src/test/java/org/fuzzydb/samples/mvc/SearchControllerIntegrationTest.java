package org.fuzzydb.samples.mvc;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.fuzzydb.samples.Person;
import org.fuzzydb.samples.config.MainConfig;
import org.fuzzydb.samples.data.DataGenerator;
import org.fuzzydb.samples.mvc.config.WebMvcConfig;
import org.fuzzydb.samples.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test that our Spring context is set up correctly
 * 
 * @author Neale
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = { MainConfig.class, WebMvcConfig.class }
		 )
public class SearchControllerIntegrationTest {

	@Autowired
	private DataGenerator gen;

	@Autowired
	private PersonRepository personRepo;

	@Test
	public void generateRandomItemShouldSaveAndRetrieve() {

		Person person = gen.createRandomPerson();
		assertNotNull(person);

		Person saved = personRepo.save(person);
		assertNotNull(saved);

		Person retrieved = personRepo.findOne(saved.getRef());
		assertNotNull(retrieved.getLocation());
	}

}
