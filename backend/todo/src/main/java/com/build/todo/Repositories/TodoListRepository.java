package com.build.todo.Repositories;

import com.build.todo.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<Task, Long>  {
}
