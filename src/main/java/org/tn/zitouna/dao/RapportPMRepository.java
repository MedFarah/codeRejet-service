package org.tn.zitouna.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tn.zitouna.entities.RapportPM;
@Repository
public interface RapportPMRepository extends MongoRepository<RapportPM, Long>{

}
