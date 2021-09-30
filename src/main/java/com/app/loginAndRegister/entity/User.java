package com.app.loginAndRegister.entity;

import com.app.loginAndRegister.enums.Rol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Date dateOff;

    @Enumerated(EnumType.STRING)
    private Rol rol;

}
