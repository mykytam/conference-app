package com.pluralsight.controllers;

import com.pluralsight.models.Speaker;
import com.pluralsight.repositories.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this will respond to payloads incoming and outgoing as JSON REST endpoints
@RequestMapping("/api/v1/speakers") // tells the router what the mapping url will look like
public class SpeakersController {
    @Autowired // autowire this when SpeakerController is built
    // it will create an instance of the speaker repository and put it onto class
    private SpeakerRepository speakerRepository;

    // list endpoint. this will return all of the speakers when called
    @GetMapping // tells which HTTP verb to use
    public List<Speaker> list() {
        return speakerRepository.findAll(); // query all of the speakers in the database and return them as a list of Speaker objects
    }

    // ability to get specific speaker by ID
    @GetMapping // HTTP verb GET, when calling method. GET pulling of the URL and injecting it into method
    @RequestMapping("{id}") // addition to class @RequestMapping. adding an additional 'id' to the URL
    public Speaker get(@PathVariable Long id) { // pass from URL to @PathVariable Long id
        return speakerRepository.getOne(id); // return and queries speakers for 'id' back to the caller in JSON payload
    }
}
