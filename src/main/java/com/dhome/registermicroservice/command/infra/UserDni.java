package com.dhome.registermicroservice.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDni {
    @Id
    @Column(length = 8)
    public Long dni;
    public Long id;

    public UserDni() {
    }

    public UserDni(Long dni, Long id) {
        this.dni = dni;
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
