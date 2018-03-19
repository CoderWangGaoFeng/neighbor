package com.neighbor.repository.sys;

import com.neighbor.module.sys.TokenModule;
import org.springframework.data.repository.CrudRepository;
import java.io.Serializable;

/**
 * token持久层
 */
public interface TokenRepository extends CrudRepository<TokenModule, Serializable>{

    public TokenModule findByToken(String token);

}
