package com.dhome.registermicroservice.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditUserRequest {
    private @Setter @Getter Long Id;
    private @Getter String Name;
    private @Getter String LastName;
    private @Getter Long Age;
    private @Getter Long DNI;
    private @Getter String Email;
    private @Getter String Password;
    private @Getter String Username;
    private @Getter String Address;

}
