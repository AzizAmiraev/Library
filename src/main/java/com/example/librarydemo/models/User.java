package com.example.librarydemo.models;

import com.example.librarydemo.enums.Role;
import com.example.librarydemo.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



// При выборе названия для столбцов и названий таблиц нужно быть осторожным, потому что есть
//зарезервированые название вида 'user' или 'group', которые используются самим Hibernate и не должны выступать
//в качестве названий переменных в ваших классах моделей

@Data
@Entity
@Table(name="usr")
@AllArgsConstructor
@NoArgsConstructor
public class User   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String groupName;
    private String phoneNumber;
    private String address;
    //Вместо email будет просто логин, менять переменную лень
    //Т.е. должно быть private String login;, но как я уже писала - мне лень
    private String email;
    @Column(length = 100)
    private String password;
    private boolean enabled;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "studentId")
    private List<Taken> takenBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "librarianId")
    private List<Taken> takenBooks1;

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<Role> getRole() {
        return role;
    }
}
