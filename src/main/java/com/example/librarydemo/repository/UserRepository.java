package com.example.librarydemo.repository;


import com.example.librarydemo.DTO.StudentsDTO;
import com.example.librarydemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsUserByFirstnameAndLastname(String firstname, String lastname);

    @Query(value = "SELECT u.id, u.address, u.email, u.firstname, u.lastname, u.group_name, u.phone_number, u.status, u.enabled, null as 'password'  FROM `usr` as u JOIN `user_role` as r on r.user_id = u.id  WHERE r.role = 'STUDENT' and u.enabled = 1" , nativeQuery = true)
    List<User> getStudents();

    @Query(value = "SELECT u.id, u.address, u.email, u.firstname, u.lastname, u.group_name, u.phone_number, u.status, u.enabled, null as 'password'  FROM `usr` as u JOIN `user_role` as r on r.user_id = u.id  WHERE r.role = 'LIBRARIAN' and u.enabled = 1" , nativeQuery = true)
    List<User> getLibrarians();

    @Query(value = "SELECT u.id, u.address, u.email, u.firstname, u.lastname, u.group_name, u.phone_number, u.status, u.enabled, null as 'password'  FROM `usr` as u JOIN `user_role` as r on r.user_id = u.id  WHERE r.role = 'ADMIN' and u.enabled = 1" , nativeQuery = true)
    List<User> getAdmins();

    @Query(value = "SELECT u.id, u.address, u.email, u.firstname, u.lastname, u.group_name, u.phone_number, u.status, u.enabled, null as 'password'  FROM `usr` as u WHERE  u.enabled = 1" , nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT u.id, u.address, u.email, u.firstname, u.lastname, u.group_name, u.phone_number, u.status, u.enabled, null as 'password'  FROM `usr` as u WHERE  u.enabled = 0" , nativeQuery = true)
    List<User> getDeletedUsers();

    @Query(value = "select * from `usr` as u where concat_ws(' ',u.lastname, u.firstname) = ?1 or concat_ws(' ', u.firstname, u.lastname) = ?1 and u.group_name is not null and u.enabled = 1" , nativeQuery = true)
    User getStudent(String fullName);
//
//    @Query(value = "SELECT * FROM `usr` WHERE `id` = ? and role_id = 1" , nativeQuery = true)
//    User getStudentById(long id);

    @Query(value="SELECT * FROM `usr` WHERE `email` = ?", nativeQuery = true)
    User getUserByUserName(String email);

    @Modifying
    @Query(value="INSERT INTO `user_role`(`user_id`, `role`) VALUES (?1, ?2)", nativeQuery = true)
    void setRole(long userId, String role);

    @Query(value="select if(COUNT(*)>0,true,false) as email from `usr` as u where u.email = ?",  nativeQuery = true)
    int existsEmail(String email);

    @Query(value="SELECT u.id, u.address, u.email, u.firstname, u.lastname, u.group_name, u.phone_number, u.status, u.enabled, null as 'password'  FROM `usr` as u JOIN `user_role` as r on r.user_id = u.id  WHERE r.role = 'STUDENT' and u.enabled = 1 and u.id = ?", nativeQuery = true)
    User getStudent(long id);

    @Modifying
    @Query(value="UPDATE `usr` SET `enabled` = 0 , `email` = null WHERE `id` = ?", nativeQuery = true)
    void deleteUser(long id);

    @Query(value="SELECT if(COUNT(*)>0,true,false) FROM `taken` WHERE `end_date` is null and `student_id` = ?", nativeQuery = true)
    int checkIfStudentHasBooks(long id);




}
