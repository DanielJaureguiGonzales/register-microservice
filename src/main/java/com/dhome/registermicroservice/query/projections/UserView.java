package com.dhome.registermicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class UserView {
    @Id @Getter @Setter
    private Long Id;
    @Column(length = 50) @Getter @Setter
    private String Name;
    @Column(length = 50) @Getter @Setter
    private String LastName;
    @Getter @Setter
    private Long Age;
    @Column(unique=true) @Getter @Setter
    private Long DNI;
    @Column(length = 50) @Email @Getter @Setter
    private String Email;
    @Column(length = 60) @Getter @Setter
    private String Password;
    @Column(length = 25) @Getter @Setter
    private String Username;
    @Column(length = 100) @Getter @Setter
    private String Address;

    public UserView() {
    }

    public UserView(Long id, String name, String lastName, Long age, Long DNI, String email, String password, String username, String address) {
        Id = id;
        Name = name;
        LastName = lastName;
        Age = age;
        this.DNI = DNI;
        Email = email;
        Password = password;
        Username = username;
        Address = address;
    }
}
