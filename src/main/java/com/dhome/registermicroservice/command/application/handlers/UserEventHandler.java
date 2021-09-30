package com.dhome.registermicroservice.command.application.handlers;

import com.dhome.registermicroservice.command.infra.UserDni;
import com.dhome.registermicroservice.command.infra.UserDniRepository;
import com.dhome.registermicroservice.contracts.events.UserEdited;
import com.dhome.registermicroservice.contracts.events.UserRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("userDni")
public class UserEventHandler {
    private final UserDniRepository userDniRepository;


    public UserEventHandler(UserDniRepository userDniRepository) {
        this.userDniRepository = userDniRepository;
    }

    @EventHandler
    public void on(UserRegistered event){
        userDniRepository.save(new UserDni(event.getDNI(), event.getId()));
    }

    @EventHandler
    public void on(UserEdited event){
        Optional<UserDni> UserDniOptional = userDniRepository.getUserDniById(event.getId());
        UserDniOptional.ifPresent(userDniRepository::delete);
        userDniRepository.save(new UserDni(event.getDNI(), event.getId()));
    }
}
