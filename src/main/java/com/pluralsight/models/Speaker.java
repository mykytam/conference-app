package com.pluralsight.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers") // name of database table
public class Speaker {
    // not camelCase, spelling here like db columns
    @Id //specify which attribute is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies how the primary key field get populated on a new INSERT
    private Long speaker_id;

    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    @ManyToMany(mappedBy = "speakers") // other side of the existing ManyToMany relationship
    private List<Session> sessions;

    public Speaker() {}

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }
}
