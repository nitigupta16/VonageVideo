package com.tfsc.ilabs.controller;

import com.opentok.OpenTok;
import com.opentok.Role;
import com.opentok.TokenOptions;
import com.opentok.exception.OpenTokException;
import com.tfsc.ilabs.couchbase.CouchbaseService;
import com.tfsc.ilabs.entities.CouchObj;
import com.tfsc.ilabs.entities.StreamObject;
import com.tfsc.ilabs.utils.VideoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niti.gupta
 */
@Slf4j
@RestController

@CrossOrigin
@RequestMapping(value = "/v1")
public class VideoRestResource {

    @Autowired
    VideoProperties videoProperties;

    @Autowired
    CouchbaseService couchbaseService;

    @PostMapping(value = "/video")
    public ResponseEntity<String> addVisitor(@RequestBody StreamObject streamObject) {
        try {
            log.info("SESSION EVENT RECEIVED {}, event: {} , timestamp  : {} ", streamObject.getSessionId(), streamObject.getEvent(), streamObject.getTimestamp());
            return new ResponseEntity<>("Session ID: "+streamObject.getSessionId()+" is successfully received",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to receive session for session ID {} ", streamObject.getSessionId(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/create/session/agentId/{agentId}")
    public ResponseEntity<Map<String,Object>> getStatus(@PathVariable String agentId) throws OpenTokException {
        CouchObj obj = couchbaseService.getSessionById(agentId);
        if(obj!=null){
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("apiKey", obj.getApiKey());
            attributes.put("sessionId", obj.getSessionId());
            attributes.put("token", obj.getToken());
            return new ResponseEntity<>(attributes, HttpStatus.OK);
        }
        OpenTok opentok = new OpenTok(videoProperties.getApiKey(), videoProperties.getApiSecret());

        //Generate a basic session. Or you could use an existing session ID.
        String sessionId = null;
        try {
            sessionId = opentok.createSession().getSessionId();
        } catch (OpenTokException e) {
            e.printStackTrace();
        }

        // Replace with meaningful metadata for the connection.
        //String connectionMetadata = "username=Bob,userLevel=4";

        // Generate a token. Use the Role value appropriate for the user.
        TokenOptions tokenOpts = new TokenOptions.Builder()
                .role(Role.MODERATOR)
                .expireTime((System.currentTimeMillis() / 1000) + (7 * 24 * 60 * 60)) // in one week
                .build();
        String token = opentok.generateToken(sessionId, tokenOpts);
        //log.info(token);
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("apiKey", videoProperties.getApiKey());
        attributes.put("sessionId", sessionId);
        attributes.put("token", token);
        CouchObj obj1 = new CouchObj();
        obj1.setAgentId(agentId);
        obj1.setApiKey(videoProperties.getApiKey());
        obj1.setSessionId(sessionId);
        obj1.setToken(token);
        couchbaseService.saveData(obj1);
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }
}
