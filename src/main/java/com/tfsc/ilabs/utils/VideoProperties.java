package com.tfsc.ilabs.utils;

import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.ReplicateTo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author niti.gupta
 */

@Configuration
@Getter
@Setter
public class VideoProperties implements Serializable {

    @Value("${api.key}")
    private int apiKey;

    @Value("${api.secret}")
    private String apiSecret;
    @Value("${couchbase.host.names}")
    private String couchbaseHostNames;

    @Value("${couchbase.queuing.bucket.name}")
    private String queuingBucketName;

    @Value("${couchbase.cluster.password}")
    private String couchbaseClusterPassword;

    @Value("${couchbase.cluster.name}")
    private String couchbaseClusterName;

    @Value("${couchbase.connect.timeout}")
    private long couchbaseConnectTimeout;

    @Value("${couchbase.query.timeout}")
    private long couchbaseQueryTimeout;

    @Value("${couchbase.search.timeout}")
    private long couchbaseSearchTimeout;

    @Value("${couchbase.kv.timeout}")
    private long kvTimeout;

    @Value("${couchbase.replicate.to}")
    private ReplicateTo replicateTo;

    @Value("${couchbase.persist.to}")
    private PersistTo persistTo;

    private List<String> couchbaseHostNamesLst;

    @PostConstruct
    private void init() {
        couchbaseHostNamesLst = Arrays.asList(couchbaseHostNames.split(","));
    }

}
