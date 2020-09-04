package com.tfsc.ilabs.entities;

import lombok.Data;

/**
 * @author niti.gupta
 */
@Data
public class StreamObject {
    private String sessionId;
    private String projectId;
    private String event;
    private long timestamp;
}
