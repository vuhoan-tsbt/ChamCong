package com.example.chamcong.repository;

import com.example.chamcong.entity.Policy;
import com.example.chamcong.repository.sql.CustomerPolicyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>, CustomerPolicyRepository {

    @Query("select policy from Policy policy where policy.namePolicy = :namePolicy")
    Optional<Policy> getByName(String namePolicy);

    @Query("select policy from Policy policy where policy.id = :id")
    Optional<Policy> findById(Integer id);

}
