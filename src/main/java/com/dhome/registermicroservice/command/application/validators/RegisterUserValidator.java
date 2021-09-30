package com.dhome.registermicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.registermicroservice.command.application.dtos.request.RegisterUserRequest;
import com.dhome.registermicroservice.command.infra.UserDni;
import com.dhome.registermicroservice.command.infra.UserDniRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class RegisterUserValidator {
    private final UserDniRepository userDniRepository;


    public RegisterUserValidator(UserDniRepository userDniRepository) {
        this.userDniRepository = userDniRepository;
    }

    public Notification validate(RegisterUserRequest registerUserRequest){
        Notification notification = new Notification();
        String Name = registerUserRequest.getName().trim();
        if (Name.isEmpty()){
            notification.addError("User Name is required");
        }
        String Lastname = registerUserRequest.getLastName().trim();
        if(Lastname.isEmpty()){
            notification.addError("User Lastname is required");
        }
        Long Age = registerUserRequest.getAge();
        if(Age == null){
            notification.addError("User Age is required");
        }
        Long DNI = registerUserRequest.getDNI();
        if(DNI==null){
            notification.addError("User DNI is required");
        }
        String Email = registerUserRequest.getEmail().trim();
        if(Email.isEmpty()){
            notification.addError("User Email is required");
        }
        String Password = registerUserRequest.getPassword().trim();
        if(Password.isEmpty()){
            notification.addError("User Password is required");
        }
        String Username = registerUserRequest.getUsername().trim();
        if(Username.isEmpty()){
            notification.addError("User Username is required");
        }
        String Address = registerUserRequest.getAddress().trim();
        if(Address.isEmpty()){
            notification.addError("User Address is required");
        }
        if (notification.hasErrors()){
            return notification;
        }
        Optional<UserDni> userDniOptional = userDniRepository.findById(DNI);
        if (userDniOptional.isPresent()){
            notification.addError("User DNI is taken");
        }
        return notification;
    }
}
