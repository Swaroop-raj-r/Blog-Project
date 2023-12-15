package com.myblog_november.repository;

import com.myblog_november.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByusername(String username); //casing is 1st word small then all words 1st letter should in caps
                                                     //findBy works as where clause of MySQL
    Optional<User> findByemail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);


    Boolean existsByusername(String username);

    Boolean existsByemail(String email);

}
