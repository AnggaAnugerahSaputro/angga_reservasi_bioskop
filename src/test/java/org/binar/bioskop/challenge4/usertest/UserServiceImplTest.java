package org.binar.bioskop.challenge4.usertest;

import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.UserRepository;
import org.binar.bioskop.challenge4.service.implement.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;


public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Get User Not Found")
    void testGetUserNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Mockito.verify(userServiceImpl.findById(Long.valueOf("not found")));
        });
    }

    @Test
    @DisplayName("Get All User Success")
    void testGetAllUserSuccess() {
        userServiceImpl.findAll();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Create User Success")
    void createUserTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.valueOf("1"));
        userEntity.setName("angga");
        userEntity.setUsername("anggaanugerah");
        userEntity.setEmail("angga@gmail.com");
        userEntity.setPassword("123456");
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Assertions.assertEquals(userEntity, userServiceImpl.create(userEntity));
    }

    @Test
    @DisplayName("Update User Success")
    void testUpdateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("angga");
        userEntity.setUsername("anggaanugerah");
        userEntity.setEmail("angga@gmail.com");
        userEntity.setPassword("123456");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.saveAndFlush(userEntity)).thenReturn(userEntity);
        var actualValue = userServiceImpl.update(1L, userEntity);
        Assertions.assertEquals(actualValue.getId(), 1L);
        Assertions.assertEquals(actualValue.getName(), "angga");
        Assertions.assertNotNull(actualValue);
    }


    @Test
    @DisplayName("Delete User Success")
    void testDeleteUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("angga");
        userEntity.setUsername("anggaanugerah");
        userEntity.setEmail("angga@gmail.com");
        userEntity.setPassword("123456");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.doNothing().when(userRepository).deleteById(1L);
        userServiceImpl.delete(1L);
        Mockito.verify(userRepository).deleteById(1L);

    }

    @Test
    @DisplayName("Get Film By Id")
    void testGetById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.valueOf("1"));
        userEntity.setName("angga");
        userEntity.setUsername("anggaanugerah");
        userEntity.setEmail("angga@gmail.com");
        userEntity.setPassword("123456");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
    }


}
