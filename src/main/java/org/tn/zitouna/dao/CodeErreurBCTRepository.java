package org.tn.zitouna.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tn.zitouna.entities.CodeErreurBCT;

@Repository
public interface CodeErreurBCTRepository extends MongoRepository<CodeErreurBCT, String> {

}
