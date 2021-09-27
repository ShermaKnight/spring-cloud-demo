package org.example.controller;

import org.example.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RemoteController {

    @Resource
    private ApplicationProperties applicationProperties;

    @GetMapping("/")
    public String getConfig() {
        return applicationProperties.toString();
    }
}
