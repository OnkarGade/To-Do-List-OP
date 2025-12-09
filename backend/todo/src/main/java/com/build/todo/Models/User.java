package com.build.todo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User extends InfoModel{

    @Column(name="fist_name", length = 30)
    private String firstName;

    @Column(name="last_name", length = 30)
    private String lastName;

    @Column(length = 30)
    private String email;

    @Column(unique = true, length = 20)
    private String password;

    @Column(name="contacts", length = 10)
    private String contactNo;

    @Column(name = "task_details", length = 50)
    private String task;

}