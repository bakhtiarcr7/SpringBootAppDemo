package com.mainpackag.firstDemoSBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    @Autowired
    Greeting greet;
    AtomicLong count = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting Greeting(@RequestParam(value = "name")String name){
        greet.setId(count.incrementAndGet());
        greet.setContent("Hellow there! Mr."+name);
        return greet;
    }
}
