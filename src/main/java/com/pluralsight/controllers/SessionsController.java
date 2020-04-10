package com.pluralsight.controllers;

import com.pluralsight.models.Session;
import com.pluralsight.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this will respond to payloads incoming and outgoing as JSON REST endpoints
@RequestMapping("/api/v1/sessions") // tells the router what the mapping url will look like
public class SessionsController {
    @Autowired // autowire this when SessionController is built
    // it will create an instance of the session repository and put it onto class
    private SessionRepository sessionRepository;

    // list endpoint. this will return all of the sessions when called
    @GetMapping // tells which HTTP verb to use
    public List<Session> list() {
        return sessionRepository.findAll(); // query all of the sessions in the database and return them as a list of Session objects
    }

    // ability to get specific session by ID
    @GetMapping // HTTP verb GET, when calling method. GET pulling of the URL and injecting it into method
    @RequestMapping("{id}") // addition to class @RequestMapping. adding an additional 'id' to the URL
    public Session get(@PathVariable Long id) { // pass from URL to @PathVariable Long id
        return sessionRepository.getOne(id); // return and queries session for 'id' back to the caller in JSON payload
    }
}
