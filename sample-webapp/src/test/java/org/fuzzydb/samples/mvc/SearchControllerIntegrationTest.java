package org.fuzzydb.samples.mvc;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.fuzzydb.samples.Person;
import org.fuzzydb.samples.repositories.PersonRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test that our Spring context is set up correctly.  This is not as easy as it should be. I want:
 * <pre>
 * {@code @ContextConfiguration}( 
 *    locations = {"root-context.xml"}, 
 *    child = {@code @ContextConfiguration}(locations = "/WEB-INF/servlet.xml") 
 *    )
 * </pre>
 * This would produce a child servlet context with a parent.  It's almost impossible to correctly test
 * servlet controller configurations without this.<br>
 * This approach would also allow a more fine grained {@code @DirtiesContext} behaviour if also providing a
 * name attribute on each context (i.e. name="root", name="servlet")
 * 
 *  In XML, <parent classes="..." or resources="..." /> would create a parent classloader.  Unfortunately, parents are expected to already
 *  exist. siblings would be possible... no.. wait.  I could create an application context, initialise it, and then register every concrete bean...
 *  
 * @author Neale
 *
 */
@Ignore("Still needs work")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml"})
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
