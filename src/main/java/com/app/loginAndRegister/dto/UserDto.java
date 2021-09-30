package com.app.loginAndRegister.dto;

import com.app.loginAndRegister.enums.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class UserDto implements Serializable{

    private static final long serialVersionUID = 1234L;
    @NotEmpty
    private UUID id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Date dateOff;

    @OneToOne
    @Enumerated(EnumType.STRING)
    private Rol rol;

}
