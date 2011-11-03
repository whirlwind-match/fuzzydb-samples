package org.fuzzydb.samples;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.fuzzydb.samples.repositories.ItemRepository;
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
	private ItemRepository itemRepo;
	
	@Test
	public void generateRandomItemShouldSaveAndRetrieve() {
		
		FuzzyItem person = gen.createRandomPerson();
		assertNotNull(person);
		
		FuzzyItem saved = itemRepo.save(person);
		assertNotNull(saved);
		
		FuzzyItem retrieved = itemRepo.findOne(saved.getRef());
		assertNotNull(retrieved.getLocation());
	}

}
