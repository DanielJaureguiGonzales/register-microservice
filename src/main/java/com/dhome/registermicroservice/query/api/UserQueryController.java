package com.dhome.registermicroservice.query.api;

import com.dhome.registermicroservice.query.projections.UserView;
import com.dhome.registermicroservice.query.projections.UserViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(tags = "User")
public class UserQueryController {
    private final UserViewRepository userViewRepository;


    public UserQueryController(UserViewRepository userViewRepository) {
        this.userViewRepository = userViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Get all users", response = List.class)
    public ResponseEntity<List<UserView>> getAll() {
        try {
            return new ResponseEntity<List<UserView>>(userViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user by id", response = UserView.class)
    public ResponseEntity<UserView> getById(@PathVariable("id") Long id) {
        try {
            Optional<UserView> userViewOptional = userViewRepository.findById(id);
            if (userViewOptional.isPresent()) {
                return new ResponseEntity<UserView>(userViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/dni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user by dni", response = UserView.class)
    public ResponseEntity<UserView> getByDocument(@PathVariable("dni") Long dni) {
        try {
            Optional<UserView> userViewOptional = userViewRepository.getByDNI(dni);
            if (userViewOptional.isPresent()) {
                return new ResponseEntity<UserView>(userViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
