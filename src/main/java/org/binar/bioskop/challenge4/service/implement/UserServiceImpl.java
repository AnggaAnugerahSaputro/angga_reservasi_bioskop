package org.binar.bioskop.challenge4.service.implement;

import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.UserRepository;
import org.binar.bioskop.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserEntity create(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity update(Long user_id, UserEntity userEntity) {
        UserEntity result = findById(user_id);
        if (result != null) {
            result.setUsername(userEntity.getUsername());
            result.setEmailaddress(userEntity.getEmailaddress());
            result.setPassword(userEntity.getPassword());
            userRepository.saveAndFlush(result);
        }
        return result;
    }

    @Override
    public Boolean delete(Long user_id) {
        final UserEntity result = findById(user_id);
        if (result != null) { // jika tidak null delete
            // hard delete
            userRepository.deleteById(user_id);
            return true; // true jika berhasil
        }
        return false;
//         userRepository.findById(user_id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long user_id) {
        Optional<UserEntity> result = userRepository.findById(user_id);
        if (result.isPresent()) {  // jika misal ada
            return result.get();
        }
        return null;
    }
}