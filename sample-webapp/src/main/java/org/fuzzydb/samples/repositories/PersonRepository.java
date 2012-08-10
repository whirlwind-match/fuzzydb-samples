package org.fuzzydb.samples.repositories;

import org.fuzzydb.samples.Person;
import org.fuzzydb.spring.repository.FuzzyRepository;

public interface PersonRepository extends FuzzyRepository<Person,String> {

}
