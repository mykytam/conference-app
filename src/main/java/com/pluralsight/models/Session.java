package com.pluralsight.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions") // name of database table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignoring
public class Session {
    // not camelCase, spelling here like db columns
    @Id //specify which attribute is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies how the primary key field get populated on a new INSERT
    private Long sessionId;

    private String sessionName;
    private String sessionDescription;
    private Integer sessionLength;

    @ManyToMany // setting up ManyToMany relationship, mapping JoinTable in DB
    @JoinTable( // defines JoinTable and foreign keys columns
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    public Session() {}

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long session_id) {
        this.sessionId = session_id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String session_name) {
        this.sessionName = session_name;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String session_description) {
        this.sessionDescription = session_description;
    }

    public Integer getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(Integer session_length) {
        this.sessionLength = session_length;
    }
}
