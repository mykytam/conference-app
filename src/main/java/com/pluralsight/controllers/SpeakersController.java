package com.pluralsight.controllers;

import com.pluralsight.models.Session;
import com.pluralsight.models.Speaker;
import com.pluralsight.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
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

    // create a new speaker. if you pass a speaker info to the API endpoint, it will create a new speaker into the database
    @PostMapping // requiring the HTTP verb POST to be presented with this API call
    public Speaker create(@RequestBody final Speaker speaker) { // Spring MVC is taking in all of the attributes in a JSON payload and automatically marshaling them into a speaker object
        return speakerRepository.saveAndFlush(speaker); // save and the flush all at once as pass JPA entities to it
    }

    // delete endpoint
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) // specifying that need to pass on an 'id', HTTP verb DELETE presented with API endpoint
    public void delete (@PathVariable Long id) { // takes id
        // also need to check for children records before deleting
        speakerRepository.deleteById(id); // delete by id
    }

    // updating speaker record
    @RequestMapping(value = "{id}", method = RequestMethod.PUT) // specifying that need to pass on an 'id', HTTP verb PUT presented with API endpoint
    // because this is a PUT, we expect all attributes to be passed in
    //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.getOne(id);
        // takes existing speakers and copies the incoming speaker data onto it
        // ignoring 'speaker_id', 'cause that a primary key, and we don't want to replace it
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker); // save and the flush all at once as pass JPA entities to it
    }
}
