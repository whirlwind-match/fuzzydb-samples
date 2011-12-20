package org.fuzzydb.samples.mvc;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.fuzzydb.samples.Person;
import org.fuzzydb.samples.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test that our Spring context is set up correctly
 * @author Neale
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:fuzzy-repository-context.xml","classpath:controllers.xml"})
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
