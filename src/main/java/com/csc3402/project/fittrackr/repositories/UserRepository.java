package com.csc3402.project.fittrackr.repositories;

import com.csc3402.project.fittrackr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user where user_id = :id", nativeQuery = true)
    User findUserById(@Param("id") int id);
}
