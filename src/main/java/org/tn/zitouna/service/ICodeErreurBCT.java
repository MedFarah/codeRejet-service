package org.tn.zitouna.service;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tn.zitouna.entities.*;
@Repository
public interface ICodeErreurBCT {
	public List<CodeErreurBCT> generateCodeErreurBCTFromFile() throws Exception;

}
