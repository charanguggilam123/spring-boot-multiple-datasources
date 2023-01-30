package com.gsc.demo.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsc.demo.domain.TODO;

@Repository
public interface TodoRepo extends JpaRepository<TODO, Long> {

}
