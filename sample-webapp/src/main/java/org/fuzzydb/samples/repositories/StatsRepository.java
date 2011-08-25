package org.fuzzydb.samples.repositories;

import org.fuzzydb.samples.MyCounter;
import org.springframework.data.repository.CrudRepository;

public interface StatsRepository extends CrudRepository<MyCounter, String> {
}
