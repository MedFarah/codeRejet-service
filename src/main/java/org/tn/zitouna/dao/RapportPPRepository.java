package org.tn.zitouna.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tn.zitouna.entities.RapportPP;
@Repository
public interface RapportPPRepository extends MongoRepository<RapportPP, Long> {

}
