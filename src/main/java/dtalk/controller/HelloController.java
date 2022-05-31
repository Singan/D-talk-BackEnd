package dtalk.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
public class HelloController {


    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }
}