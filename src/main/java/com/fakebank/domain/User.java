package com.fakebank.domain;

/**
 * Created by Javan on 9/17/16.
 */
import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Date created;
    private Date updated;


    private User(){}

    public User(String first, String last){
        this.setFirstName(first);
        this.setLastName(last);
    }

    public User(String first, String last, String email){
        this.setFirstName(first);
        this.setLastName(last);
        this.setEmail(email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    protected void onCreate(){
        created = updated = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updated = new Date();
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
    }


}