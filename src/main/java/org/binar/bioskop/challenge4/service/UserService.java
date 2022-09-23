package org.binar.bioskop.challenge4.service;

import org.binar.bioskop.challenge4.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create (UserEntity userEntity);
    UserEntity update(Long user_id, UserEntity userEntity);
    Boolean delete(Long user_id);

    List<UserEntity> findAll();
    UserEntity findById(Long id);


}
