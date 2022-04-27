package org.tn.zitouna.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tn.zitouna.entities.RapportOperationDevise;
@Repository
public interface RapportOperationDeviseRepository extends MongoRepository<RapportOperationDevise, Long> {

}
