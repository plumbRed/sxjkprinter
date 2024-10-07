package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello", description = "Hello API")
public class HelloController {

    @Operation(summary = "Say hello", description = "Returns a greeting message")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}