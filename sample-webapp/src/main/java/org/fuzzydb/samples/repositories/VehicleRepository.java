package org.fuzzydb.samples.repositories;


import org.fuzzydb.samples.Vehicle;

import com.wwm.db.spring.repository.FuzzyRepository;

public interface VehicleRepository extends FuzzyRepository<Vehicle,String> {

}
