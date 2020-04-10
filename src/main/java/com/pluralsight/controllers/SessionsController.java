package com.pluralsight.controllers;

import com.pluralsight.models.Session;
import com.pluralsight.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//full CRUD controller
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

    // create a new session. if you pass a session info to the API endpoint, it will create a new session into the database
    @PostMapping // requiring the HTTP verb POST to be presented with this API call
    public Session create(@RequestBody final Session session) { // Spring MVC is taking in all of the attributes in a JSON payload and automatically marshaling them into a session object
        return sessionRepository.saveAndFlush(session); // save and the flush all at once as pass JPA entities to it
    }

    // delete endpoint
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) // specifying that need to pass on an 'id', HTTP verb DELETE presented with API endpoint
    public void delete (@PathVariable Long id) { // takes id
        // also need to check for children records before deleting
        sessionRepository.deleteById(id); // delete by id
    }

    // updating session record
    @RequestMapping(value = "{id}", method = RequestMethod.PUT) // specifying that need to pass on an 'id', HTTP verb PUT presented with API endpoint
    // because this is a PUT, we expect all attributes to be passed in
    //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id);
        // takes existing session and copies the incoming session data onto it
        // ignoring 'session_id', 'cause that a primary key, and we don't want to replace it
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession); // save and the flush all at once as pass JPA entities to it
    }
}