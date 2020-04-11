package com.pluralsight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers") // name of database table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignoring
public class Speaker {
    // not camelCase, spelling here like db columns
    @Id //specify which attribute is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies how the primary key field get populated on a new INSERT
    private Long speakerId;

    private String firstName;
    private String lastName;
    private String title;
    private String company;
    private String speakerBio;

    @Lob // large object
    @Type(type = "org.hibernate.type.BinaryType") // help hibernate dealing with binary data
    private byte[] speaker_photo; // byte array

    @ManyToMany(mappedBy = "speakers") // other side of the existing ManyToMany relationship
    @JsonIgnore
    private List<Session> sessions;

    public Speaker() {}

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Long speaker_id) {
        this.speakerId = speaker_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
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

    public String getSpeakerBio() {
        return speakerBio;
    }

    public void setSpeakerBio(String speaker_bio) {
        this.speakerBio = speaker_bio;
    }
}
