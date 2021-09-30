package com.dhome.registermicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.registermicroservice.command.application.dtos.request.EditUserRequest;
import com.dhome.registermicroservice.command.application.dtos.request.RegisterUserRequest;
import com.dhome.registermicroservice.command.application.dtos.response.EditUserResponse;
import com.dhome.registermicroservice.command.application.dtos.response.RegisterUserResponse;
import com.dhome.registermicroservice.command.application.validators.EditUserValidator;
import com.dhome.registermicroservice.command.application.validators.RegisterUserValidator;
import com.dhome.registermicroservice.command.infra.UserDniRepository;
import com.dhome.registermicroservice.contracts.commands.EditUser;
import com.dhome.registermicroservice.contracts.commands.RegisterUser;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class UserApplicationService {
    private final RegisterUserValidator registerUserValidator;
    private final EditUserValidator editUserValidator;
    private final CommandGateway commandGateway;
    private final UserDniRepository userDniRepository;


    public UserApplicationService(RegisterUserValidator registerUserValidator, EditUserValidator editUserValidator, CommandGateway commandGateway, UserDniRepository userDniRepository) {
        this.registerUserValidator = registerUserValidator;
        this.editUserValidator = editUserValidator;
        this.commandGateway = commandGateway;
        this.userDniRepository = userDniRepository;
    }

    public Result<RegisterUserResponse, Notification> register(RegisterUserRequest registerUserRequest) throws Exception {
        int i =2;
        Notification notification = this.registerUserValidator.validate(registerUserRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Long Id = Long.valueOf(i);
        i++;
        RegisterUser registerUser = new RegisterUser(
                Id,
                registerUserRequest.getName().trim(),
                registerUserRequest.getLastName().trim(),
                registerUserRequest.getAge(),
                registerUserRequest.getDNI(),
                registerUserRequest.getEmail(),
                registerUserRequest.getPassword(),
                registerUserRequest.getUsername(),
                registerUserRequest.getAddress()
        );
        CompletableFuture<Object> future = commandGateway.send(registerUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterUserResponse registerUserResponseDto = new RegisterUserResponse(
                registerUser.getId(),
                registerUser.getName().trim(),
                registerUser.getLastName().trim(),
                registerUser.getAge(),
                registerUser.getDNI(),
                registerUser.getEmail(),
                registerUser.getPassword(),
                registerUser.getUsername(),
                registerUser.getAddress()
        );
        return Result.success(registerUserResponseDto);
    }

    /*public Result<EditUserResponse, Notification> edit(EditUserRequest editUserRequest) throws Exception {
        Notification notification = this.editUserValidator.validate(editUserRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditUser editUser = new EditUser(
                editUserRequest.getId(),
                editUserRequest.getName().trim(),
                editUserRequest.getLastName().trim(),
                editUserRequest.getAge(),
                editUserRequest.getDNI(),
                editUserRequest.getEmail(),
                editUserRequest.getPassword(),
                editUserRequest.getUsername(),
                editUserRequest.getAddress()
        );
        CompletableFuture<Object> future = commandGateway.send(editUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditUserResponse editUserResponse = new EditUserResponse(
                editUser.getId(),
                editUser.getName().trim(),
                editUser.getLastName().trim(),
                editUser.getAge(),
                editUser.getDNI(),
                editUser.getEmail(),
                editUser.getPassword(),
                editUser.getUsername(),
                editUser.getAddress()
        );
        return Result.success(editUserResponse);
    }*/
}
