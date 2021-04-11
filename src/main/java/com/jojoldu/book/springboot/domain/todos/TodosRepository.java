package com.jojoldu.book.springboot.domain.todos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodosRepository extends JpaRepository<Todos,Long> {
    List<Todos> findAllByType(String type);
}
