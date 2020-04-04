package com.pluralsight.service;

import com.pluralsight.model.Speaker;
import com.pluralsight.repository.SpeakerRepository;
import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository; // SpeakerServiceImpl now injected, not hardcoded

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        repository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }

}
