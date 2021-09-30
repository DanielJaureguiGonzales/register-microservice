package com.dhome.registermicroservice.command.api;


import com.dhome.common.api.ApiController;


import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.registermicroservice.command.application.dtos.request.EditUserRequest;
import com.dhome.registermicroservice.command.application.dtos.request.RegisterUserRequest;
import com.dhome.registermicroservice.command.application.dtos.response.EditUserResponse;
import com.dhome.registermicroservice.command.application.dtos.response.RegisterUserResponse;
import com.dhome.registermicroservice.command.application.services.UserApplicationService;
import com.dhome.registermicroservice.command.infra.UserDniRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(tags="Users")
public class UserCommandController {
    private final UserApplicationService userApplicationService;
    private final CommandGateway commandGateway;
    private final UserDniRepository userDniRepository;


    public UserCommandController(UserApplicationService userApplicationService, CommandGateway commandGateway, UserDniRepository userDniRepository) {
        this.userApplicationService = userApplicationService;
        this.commandGateway = commandGateway;
        this.userDniRepository = userDniRepository;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            Result<RegisterUserResponse, Notification> result = userApplicationService.register(registerUserRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

    /*@PutMapping("/{userId}")
    public ResponseEntity<Object> edit(@PathVariable("userId") Long userId, @RequestBody EditUserRequest editUserRequest) {
        try {
            editUserRequest.setId(userId);
            Result<EditUserResponse, Notification> result = userApplicationService.edit(editUserRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }*/


}
