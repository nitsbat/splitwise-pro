package com.app.splitwise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @GetMapping(value = "/name")
    public List<String> getNames() {
        return List.of("Nitin", "Bisht");
    }

}
