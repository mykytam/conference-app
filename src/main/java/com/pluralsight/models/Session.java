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
    private Long session_id;

    private String session_name;
    private String session_description;
    private Integer session_length;

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

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
