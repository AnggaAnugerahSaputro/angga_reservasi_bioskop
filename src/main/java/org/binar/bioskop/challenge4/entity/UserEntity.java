package org.binar.bioskop.challenge4.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "username")
    private String username;

    @Column(name = "emailaddress")
    private String emailaddress;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "password")
    private String password;

}
