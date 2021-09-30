package com.dhome.registermicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.registermicroservice.command.application.dtos.request.EditUserRequest;
import com.dhome.registermicroservice.command.domain.User;
import com.dhome.registermicroservice.command.infra.UserDni;
import com.dhome.registermicroservice.command.infra.UserDniRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;

import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class EditUserValidator {
   /* private final UserDniRepository userDniRepository;
    private final Repository<User> userRepository;

    public EditUserValidator(UserDniRepository userDniRepository, Repository<User> userRepository) {
        this.userDniRepository = userDniRepository;
        this.userRepository = userRepository;
    }


    public Notification validate(EditUserRequest editUserRequest){
        Notification notification = new Notification();
        Long Id = editUserRequest.getId();
        if (Id==null){
            notification.addError("User Id is required");
        }
        String Name = editUserRequest.getName().trim();
        if (Name.isEmpty()){
            notification.addError("User Name is required");
        }
        String Lastname = editUserRequest.getLastName().trim();
        if(Lastname.isEmpty()){
            notification.addError("User Lastname is required");
        }
        Long Age = editUserRequest.getAge();
        if(Age == null){
            notification.addError("User Age is required");
        }
        Long DNI = editUserRequest.getDNI();
        if(DNI==null){
            notification.addError("User DNI is required");
        }
        String Email = editUserRequest.getPassword().trim();
        if(Email.isEmpty()){
            notification.addError("User Email is required");
        }
        String Password = editUserRequest.getPassword().trim();
        if(Password.isEmpty()){
            notification.addError("User Password is required");
        }
        String Username = editUserRequest.getUsername().trim();
        if(Username.isEmpty()){
            notification.addError("User Username is required");
        }
        String Address = editUserRequest.getAddress().trim();
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

    private void loadUserAggregate(String userId){
        UnitOfWork unitOfWork = null;
        try{
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            userRepository.load(userId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex){
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
*/
}
