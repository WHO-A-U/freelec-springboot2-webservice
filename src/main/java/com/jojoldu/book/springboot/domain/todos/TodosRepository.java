package com.jojoldu.book.springboot.domain.todos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todos,Long> {

}
