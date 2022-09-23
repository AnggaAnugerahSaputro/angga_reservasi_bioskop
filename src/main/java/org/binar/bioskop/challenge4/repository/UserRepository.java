package org.binar.bioskop.challenge4.repository;

import org.binar.bioskop.challenge4.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {


}
