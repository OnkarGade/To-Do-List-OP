package com.build.todo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Task extends InfoModel{

    @Column(name= "task", length = 50)
    private String taskDetails;

    @Column(name="start")
    private LocalDate startOn;

    @Column(name="end")
    private LocalDate endsOn;
}

