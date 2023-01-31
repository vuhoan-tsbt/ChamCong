package com.example.chamcong.controller;

import com.example.chamcong.dto.ZoomMeetingObjectDTO;
import com.example.chamcong.dto.ZoomMeetingSettingsDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.security.KeyStoreException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RequestMapping(value = "/zoom/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class    ZoomController {
    Logger log =  LoggerFactory.getLogger(ZoomController.class);

    @GetMapping("/meeting")
    public ResponseEntity<?> createMeeting() throws KeyStoreException {
        ZoomMeetingObjectDTO zoomMeetingObjectDTO = new ZoomMeetingObjectDTO();
        String apiUrl = "https://api.zoom.us/v2/users/" + "me" + "/meetings";
        zoomMeetingObjectDTO.setDefault_password(true);
        zoomMeetingObjectDTO.setHost_email("vuhoan485@gmail.com");
        ZoomMeetingSettingsDTO settingsDTO = new ZoomMeetingSettingsDTO();
        settingsDTO.setJoin_before_host(false);
        settingsDTO.setParticipant_video(true);
        settingsDTO.setHost_video(false);
        settingsDTO.setAuto_recording("cloud");
        settingsDTO.setMute_upon_entry(true);
        zoomMeetingObjectDTO.setType(1);
        settingsDTO.setMeeting_authentication(true);

        zoomMeetingObjectDTO.setSettings(settingsDTO);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + generateZoomJWTToken());
        headers.add("content-type", "application/json");
        HttpEntity<ZoomMeetingObjectDTO> httpEntity = new HttpEntity<ZoomMeetingObjectDTO>(zoomMeetingObjectDTO, headers);
        ResponseEntity<ZoomMeetingObjectDTO> zEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ZoomMeetingObjectDTO.class);
        if(zEntity.getStatusCodeValue() == 201) {
            log.debug("Zooom meeeting response {}",zEntity);
            Map<String, Object> meetCredentials = new HashMap<>();
            meetCredentials.put("start_url", zEntity.getBody().getStart_url());
            meetCredentials.put("join_url", zEntity.getBody().getJoin_url());
            return ResponseEntity.ok().body(meetCredentials);
        } else {
            log.debug("Error while creating zoom meeting {}", zEntity.getStatusCode());
        }
        Map<String, Object> meetCredentials = new HashMap<>();
        meetCredentials.put("start_url", zoomMeetingObjectDTO.getStart_url());
        meetCredentials.put("join_url", zoomMeetingObjectDTO.getJoin_url());
        return ResponseEntity.ok().body(meetCredentials);
    }
    private String generateZoomJWTToken() {
        String id = UUID.randomUUID().toString().replace("-", "");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date creation = new Date(System.currentTimeMillis());
        Date tokenExpiry = new Date(System.currentTimeMillis() + (1000 * 60));
        String zoomApiSecret = "mOEkOkYddWhHHCr4Nhsm4WpR4ntcG79sCe4w";

        Key key = Keys.hmacShaKeyFor(zoomApiSecret.getBytes());
        return Jwts.builder()
                .setId(id)
                .setIssuer("VdKchwGgSzuAMu0hP2C4Ag")
                .setIssuedAt(creation)
                .setSubject("")
                .setExpiration(tokenExpiry)
                .signWith(signatureAlgorithm, key)
                .compact();
    }
}
