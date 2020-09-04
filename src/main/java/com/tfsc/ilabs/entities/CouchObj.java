package com.tfsc.ilabs.entities;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import org.springframework.data.annotation.Version;

/**
 * @author niti.gupta
 */
@Data
public class CouchObj {
    @Id
    private String agentId;

    @Version
    private long version;

    @Field
    private int apiKey;

    @Field
    private String sessionId;

    @Field
    private String token;
}
