package org.fuzzydb.samples.repositories;

import org.fuzzydb.samples.FuzzyItem;

import com.wwm.db.spring.repository.FuzzyRepository;

public interface ItemRepository extends FuzzyRepository<FuzzyItem,String> {

}
