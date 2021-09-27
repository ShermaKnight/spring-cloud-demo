package org.example.controller;

import org.example.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/")
    public boolean send() {
        return messageProvider.send();
    }
}
