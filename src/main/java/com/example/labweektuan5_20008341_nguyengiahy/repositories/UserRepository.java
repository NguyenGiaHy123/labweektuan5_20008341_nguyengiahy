package com.example.labweektuan5_20008341_nguyengiahy.repositories;

import com.example.labweektuan5_20008341_nguyengiahy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

}
