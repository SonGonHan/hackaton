package com.hackaton.task.gigachat.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gigachat")
public class GigaChatController {

   public ResponseEntity<String> sendRequest(String message) {
      String response = GigaChatConnection.sendRequest(message);

      return ResponseEntity.ok(response);
   }
}
