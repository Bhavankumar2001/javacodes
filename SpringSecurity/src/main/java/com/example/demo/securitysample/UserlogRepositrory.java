package com.example.demo.securitysample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UserlogRepositrory extends JpaRepository<UserLog, Integer> {

}
