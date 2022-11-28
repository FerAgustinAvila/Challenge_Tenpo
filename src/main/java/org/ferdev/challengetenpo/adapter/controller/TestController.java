package org.ferdev.challengetenpo.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(new MessageResponse("Test ok"));
    }
}
