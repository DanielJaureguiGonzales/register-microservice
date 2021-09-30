package com.dhome.registermicroservice.query.projections;

import com.dhome.registermicroservice.contracts.events.UserRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class UserViewProjection {
    private final UserViewRepository userViewRepository;


    public UserViewProjection(UserViewRepository userViewRepository) {
        this.userViewRepository = userViewRepository;
    }

    @EventHandler
    public void on(UserRegistered event, @Timestamp Instant timestamp){
        Optional<UserView> userViewOptional = userViewRepository.findById(event.getId());
        if (userViewOptional.isPresent()){
            UserView userView = userViewOptional.get();
            userView.setName(event.getName());
            userView.setLastName(event.getLastName());
            userView.setAge(event.getAge());
            userView.setDNI(event.getDNI());
            userView.setEmail(event.getEmail());
            userView.setPassword(event.getPassword());
            userView.setUsername(event.getUsername());
            userView.setAddress(event.getAddress());
            userViewRepository.save(userView);

        }
    }
}
