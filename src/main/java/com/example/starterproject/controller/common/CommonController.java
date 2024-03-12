package com.example.starterproject.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommonController {
    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.status(HttpStatus.OK)
                .body("Hello World!");
    }
}
