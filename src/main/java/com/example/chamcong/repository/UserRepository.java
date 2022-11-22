package com.example.chamcong.repository;

import com.example.chamcong.entity.Employee;
import com.example.chamcong.entity.User;
import com.example.chamcong.repository.sql.CustomerUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CustomerUserRepository {

    @Query(value = "select count(*) from  d13cnpm7chamcong.user", nativeQuery = true)
    Long countUser();

    @Query("select user from User user left join fetch user.role where user.email = :email")
    User getUserWithRoleByEmail(String email);

    @Query("select manager from User manager where manager.email = :email")
    Optional<User> getByEmail(String email);

    @Query("from User where email = :email and deleted=0")
    Optional<User> findByEmail(String email);

    @Query("select employee from Employee employee where employee.id = :id")
    Optional<User> findById(int id);

    @Query("select user from User user where user.id = :id")
    User getById(int id);

    @Query("select user from User user where user.staffCode = :code")
    Optional<Employee> getByStaffCode(String code);

    @Query("select user from User user where user.id = :id")
    Optional<User> findByIdPassword(int id);

    @Query("select manager from Manager manager where manager.email = :email")
    User getByEmailToken(String email);

    @Query(value = "select parent from User parent where parent.phone = :phone")
    Optional<User> getByPhone(String phone);

    Optional<User> findByAvatar(String fileName);


}
