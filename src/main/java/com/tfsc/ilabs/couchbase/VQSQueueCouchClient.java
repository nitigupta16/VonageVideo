package com.tfsc.ilabs.couchbase;

import com.tfsc.ilabs.assist.couchbaseconnector.service.CouchbaseDBClient;
import com.tfsc.ilabs.assist.couchbaseconnector.service.impl.CouchbaseDBClientImpl;
import com.tfsc.ilabs.utils.VideoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author niti.gupta
 */
@Slf4j
@Configuration
public class VQSQueueCouchClient {

    @Bean
    public CouchbaseDBClient getCouchbaseQueueClient(VideoProperties videoProperties) {
        CouchbaseDBClientImpl couchbaseDBClient = null;
        try {
            couchbaseDBClient = new CouchbaseDBClientImpl(videoProperties.getCouchbaseHostNamesLst(),
                    videoProperties.getCouchbaseClusterName(), videoProperties.getCouchbaseClusterPassword(),
                    videoProperties.getQueuingBucketName(), videoProperties.getCouchbaseConnectTimeout(),
                    videoProperties.getCouchbaseQueryTimeout(), videoProperties.getCouchbaseSearchTimeout(),
                    videoProperties.getKvTimeout(), videoProperties.getPersistTo(),
                    videoProperties.getReplicateTo());
        } catch (Exception e) {
            log.error("Couchbase connectivity has failed. Shutting Down Application.",e.getMessage(),e);
            System.exit(1);
        }
        return couchbaseDBClient;
    }
}