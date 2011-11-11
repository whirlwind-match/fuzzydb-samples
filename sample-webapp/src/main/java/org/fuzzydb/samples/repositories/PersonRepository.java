package org.fuzzydb.samples.repositories;

import org.fuzzydb.samples.Person;

import com.wwm.db.spring.repository.FuzzyRepository;

public interface PersonRepository extends FuzzyRepository<Person,String> {

}
