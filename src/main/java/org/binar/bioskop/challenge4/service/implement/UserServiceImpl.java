package org.binar.bioskop.challenge4.service.implement;

import lombok.RequiredArgsConstructor;

import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.UserRepository;
import org.binar.bioskop.challenge4.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity userEntity) {
        logger.info("Saving new user {} to Database", userEntity.getName());
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity update(Long id, UserEntity userEntity) {
        UserEntity result = findById(id);
        if (result != null) {
            result.setName(userEntity.getName());
            result.setUsername(userEntity.getUsername());
            result.setEmail(userEntity.getEmail());
            result.setPassword(userEntity.getPassword());
            userRepository.saveAndFlush(result);
        }else {
            logger.error("Update Data user not found");
        }
        return result;
    }

    @Override
    public Boolean delete(Long id) {
        final UserEntity result = findById(id);
        if (result != null) { // jika tidak null delete
            // hard delete
            userRepository.deleteById(id);
            return true; // true jika berhasil
        }else {
            logger.error("Data user not found");
        }
        return false;
    }

    @Override
    public List<UserEntity> findAll() {
        logger.info("Fetching data user from database");
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        logger.info("Fetching data user by id {}", id);
        Optional<UserEntity> result = userRepository.findById(id);
        if (result.isPresent()) {  // jika misal ada
            return result.get();
        }else {
            logger.error("Data user not found");
        }
        return null;
    }

}