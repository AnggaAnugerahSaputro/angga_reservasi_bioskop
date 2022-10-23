package org.binar.bioskop.challenge4.service;

import org.binar.bioskop.challenge4.entity.Role;
import org.binar.bioskop.challenge4.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create (UserEntity userEntity);
    UserEntity update(Long id, UserEntity userEntity);
    Boolean delete(Long id);

    List<UserEntity> findAll();
    UserEntity findById(Long id);



}
