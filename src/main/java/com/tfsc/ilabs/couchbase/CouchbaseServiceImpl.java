package com.tfsc.ilabs.couchbase;

import com.tfsc.ilabs.assist.couchbaseconnector.service.CouchbaseDBClient;
import com.tfsc.ilabs.entities.CouchObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author niti.gupta
 */
@Slf4j
@Component
public class CouchbaseServiceImpl implements CouchbaseService {
    @Autowired
    CouchbaseDBClient couchbaseDBClient;
    @Override
    public void saveData(CouchObj obj) {
        try{
            couchbaseDBClient.create(obj);
        }catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public CouchObj getSessionById(String assId){
        CouchObj obj = null;
        try {
           obj = couchbaseDBClient.findById(assId, CouchObj.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return obj;
    }

}
