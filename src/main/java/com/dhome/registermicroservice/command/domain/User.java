package com.dhome.registermicroservice.command.domain;

import com.dhome.registermicroservice.contracts.commands.EditUser;
import com.dhome.registermicroservice.contracts.commands.RegisterUser;
import com.dhome.registermicroservice.contracts.events.UserEdited;
import com.dhome.registermicroservice.contracts.events.UserRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {
    @AggregateIdentifier
    private Long Id;
    private String Name;
    private String LastName;
    private Long Age;
    private Long DNI;
    private String Password;
    private String Username;
    private String Address;

    public User(){

    }

    @CommandHandler
    public User(RegisterUser command){
        Instant now = Instant.now();
        apply(
            new UserRegistered(
                    command.getId(),
                    command.getName(),
                    command.getLastName(),
                    command.getAge(),
                    command.getDNI(),
                    command.getPassword(),
                    command.getUsername(),
                    command.getAddress(),
                    now
            )
        );
    }
    @CommandHandler
    public void handle(EditUser command){
        Instant now = Instant.now();
        apply(
                new UserEdited(
                        command.getId(),
                        command.getName(),
                        command.getLastName(),
                        command.getAge(),
                        command.getDNI(),
                        command.getPassword(),
                        command.getUsername(),
                        command.getAddress(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on(UserRegistered event){
        Id=event.getId();
        Name= event.getName();
        LastName = event.getLastName();
        Age=event.getAge();
        DNI=event.getDNI();
        Password=event.getPassword();
        Username=event.getUsername();
        Address=event.getAddress();
    }

    @EventSourcingHandler
    protected void on(UserEdited event){
        Name= event.getName();
        LastName= event.getLastName();
        Age=event.getAge();
        DNI=event.getDNI();
        Username=event.getUsername();
        Address=event.getAddress();
    }
}
