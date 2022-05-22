package com.example.librarydemo.services;


import com.example.librarydemo.DTO.LibrarianAdminDTO;
import com.example.librarydemo.DTO.StudentDTO;
import com.example.librarydemo.enums.Role;
import com.example.librarydemo.enums.Status;
import com.example.librarydemo.exceptions.CustomException;
import com.example.librarydemo.models.User;
import com.example.librarydemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository1) {
        this.userRepository = userRepository1;
    }

    // Достать всех пользователей. Выводит список всех пользователей
    public List<User> UserList(){
        return userRepository.getAllUsers();
    }

    public List<User> deletedUserList(){
        return userRepository.getDeletedUsers();
    }

    public List<User> getLibrarians(){
        return userRepository.getLibrarians();
    }

    public List<User> getAdmins(){
        return userRepository.getAdmins();
    }

    //Достает всю информацию об одном пользователе
    public User oneUser(long id){
        return userRepository.findById(id).get();
    }

    //Создание User
    public User createUser(User user){
        return userRepository.save(user);
    }

    //Удаление user не зависимо от его роли
    public void deleteUser(long id) throws CustomException{
        if(userRepository.checkIfStudentHasBooks(id) == 0) {
            userRepository.deleteUser(id);
        }else{
            throw new CustomException("Student Has taken Books");
        }
    }

    public User getStudent(long id){
        return userRepository.getStudent(id);
    }

    //Нахождение user по ID, потом понадобится
    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    // Редактирует информацию об определнном студенте
    public void editStudent(User student){
        String password;
        if(student.getPassword() == null){
            password = userRepository.getStudent(student.getId()).getPassword();
        }else{
            password = passwordEncoder.encode(student.getPassword());
        }

        student.setPassword(password);

        userRepository.save(student);

    }
    // Редактирует информацию об определенном библиотекаре или админе
    public void editLibrarian(LibrarianAdminDTO librarianDTO){
        User librarian = userRepository.getById(librarianDTO.getId());
        librarian.setFirstname(librarianDTO.getFirstname());
        librarian.setLastname(librarianDTO.getLastname());
        librarian.setPhoneNumber(librarianDTO.getPhoneNumber());
        librarian.setEmail(librarianDTO.getLogin());
        librarian.setPassword(librarianDTO.getPassword());
    }

    public List<User> getStudents(){
        return userRepository.getStudents();
    }

    @Transactional
    public boolean createStudent(StudentDTO studentDTO) {


        if(userRepository.existsEmail(studentDTO.getLogin()) == 1){
            return false;
        }

        String encodedPassword = passwordEncoder.encode(studentDTO.getPassword());

        User user = new User();

        user.setLastname(studentDTO.getLastname());
        user.setFirstname(studentDTO.getFirstname());
        user.setEmail(studentDTO.getLogin());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(studentDTO.getPhoneNumber());
        user.setAddress(studentDTO.getAddress());
        user.setGroupName(studentDTO.getGroup());

        user.setEnabled(true);
        user.setStatus(Status.ACTIVE);
        User u = userRepository.save(user);

        userRepository.setRole(u.getId(), Role.STUDENT.toString());
        return true;
    }

    @Transactional
    public boolean createAdmin(StudentDTO studentDTO) {


        if(userRepository.existsEmail(studentDTO.getLogin()) == 1){
            return false;
        }
        String encodedPassword = passwordEncoder.encode(studentDTO.getPassword());

        User user = new User();

        user.setLastname(studentDTO.getLastname());
        user.setFirstname(studentDTO.getFirstname());
        user.setEmail(studentDTO.getLogin());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(studentDTO.getPhoneNumber());
        user.setAddress(studentDTO.getAddress());

        user.setEnabled(true);
        user.setStatus(Status.ACTIVE);
        User u = userRepository.save(user);

        userRepository.setRole(u.getId(), Role.ADMIN.toString());
        return true;
    }

    @Transactional
    public boolean createLibrarian(StudentDTO studentDTO) {


        if(userRepository.existsEmail(studentDTO.getLogin()) == 1){
            return false;
        }

        String encodedPassword = passwordEncoder.encode(studentDTO.getPassword());

        User user = new User();

        user.setLastname(studentDTO.getLastname());
        user.setFirstname(studentDTO.getFirstname());
        user.setEmail(studentDTO.getLogin());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(studentDTO.getPhoneNumber());
        user.setAddress(studentDTO.getAddress());

        user.setEnabled(true);
        user.setStatus(Status.ACTIVE);
        User u = userRepository.save(user);

        userRepository.setRole(u.getId(), Role.LIBRARIAN.toString());
        return true;
    }




    //Создание Библиотекаря и Админа
//    public ResponseMessage createLibrarianOrAdmin(User worker){
//        if(!userRepository.existsUserByFirstnameAndLastname(worker.getFirstname(), worker.getLastname())){
//            worker.setEmail(worker.getEmail());
//            worker.setPassword(worker.getPassword());
//            worker.setFirstname(worker.getFirstname());
//            worker.setLastname(worker.getLastname());
//            worker.setPhoneNumber(worker.getPhoneNumber());
//            worker.setAddress(worker.getAddress());
//            worker.setRole(worker.getRole());
//            worker.setEnabled(true);
//            userRepository.save(worker);
//            return new ResponseMessage("Librarian successfully added");
//
//        }return new ResponseMessage("хрен там, ошибка");
//    }


    //        User student = new User();
//        student.setFirstname(studentDTO.getFirstname());
//        student.setLastname(studentDTO.getLastname());
//        student.setGroupName(studentDTO.getGroup());
//        student.setPhoneNumber(studentDTO.getPhoneNumber());
//        student.setAddress(studentDTO.getAddress());
//        student.setEmail(studentDTO.getLogin());
//        student.setPassword(studentDTO.getPassword());


//
//    public void addStudent(Student student){
//        studentRepository.save(student);
//    }
//
//    public void creatAdmin(User admin){
//        admin.setEnabled(true);
//        admin.setRoleId(roleRepository.findById(3).get());
//        UserRepository.save(admin);
//    }
//
//    public void creatLibrarian(User librarian){
//        librarian.setEnabled(true);
//        librarian.setRoleId(roleRepository.findById(2).get());
//        UserRepository.save(librarian);
//    }
//
//    public void creatStudent(User student){
//        student.setEnabled(true);
//        student.setRoleId(roleRepository.findById(1).get());
//        UserRepository.save(student);
//    }
//


//
//    public StudentDTO getStudent(int id){
//        User user = UserRepository.getStudentById(id);
//
//        System.out.println(user.getId());
//        Student student = studentRepository.getStudentById(user.getId());
//
//        StudentDTO studentDTO = new StudentDTO();
//
//        studentDTO.setId(user.getId());
//        studentDTO.setEmail(user.getEmail());
//        studentDTO.setAddress(student.getAddress());
//        studentDTO.setGroup(student.getGrop());
//        studentDTO.setLogin(user.getLogin());
//        studentDTO.setPassword(user.getPassword());
//        studentDTO.setPhone(user.getPhoneNumber());
//        studentDTO.setFullName(user.getFullName());
//
//        return studentDTO;
//    }
//
//    public void editStudent(StudentDTO studentDTO){
//
//
//        User user = UserRepository.getStudentById(studentDTO.getId());
//
//        user.setEmail(studentDTO.getEmail());
//        user.setFullName(studentDTO.getFullName());
//        user.setLogin(studentDTO.getLogin());
//        user.setPassword(studentDTO.getPassword());
//        user.setPhoneNumber(studentDTO.getPhone());
//
//        UserRepository.save(user);
//
//        Student student = studentRepository.getStudentById(user.getId());
//
//        student.setAddress(studentDTO.getAddress());
//        student.setGrop(studentDTO.getGroup());
//
//        studentRepository.save(student);
//
//
//    }



//    //Выводит всю информацию об определенном студенте
//    public StudentDTO allInformationAboutOneStudent(int id){
//        User user = userRepository.getById(id);
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setFirstname(user.getFirstname());
//        studentDTO.setLastname(user.getLastname());
//        studentDTO.setGroup(user.getGroupName());
//        studentDTO.setPhoneNumber(user.getPhoneNumber());
//        studentDTO.setAddress(user.getAddress());
//        studentDTO.setLogin(user.getEmail());
//        studentDTO.setPassword(user.getPassword());
//        return studentDTO;
//    }



    //    //Выводит всю информацию об определенном библиотекаре или админе
//    public LibrarianAdminDTO allInformationAboutOneLibrarian(int id){
//        User user = userRepository.getById(id);
//        LibrarianAdminDTO librarianDTO = new LibrarianAdminDTO();
//        librarianDTO.setFirstname(user.getFirstname());
//        librarianDTO.setLastname(user.getLastname());
//        librarianDTO.setPhoneNumber(user.getPhoneNumber());
//        librarianDTO.setLogin(user.getEmail());
//        librarianDTO.setPassword(user.getPassword());
//        return librarianDTO;
//    }



}
