package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {
    @RequestMapping("/")
    public String getHelloWorld () {
        return "Hello World";
    }
}
