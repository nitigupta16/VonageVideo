package com.tfsc.ilabs.couchbase;

import com.tfsc.ilabs.entities.CouchObj;
import org.springframework.stereotype.Component;

/**
 * @author niti.gupta
 */
@Component
public interface CouchbaseService {

    void saveData(CouchObj obj);

     CouchObj getSessionById(String assId);
}
