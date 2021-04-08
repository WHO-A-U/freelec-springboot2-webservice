package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts,Long>{
    //extends < [Entity class type] , [PK type] > 을 지정해주면 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    //SPringDataJpa 에서 제공되는 메서드가 아닐경우 @Query 를 이용하여 작성함
    List<Posts> findAllDesc();
}
