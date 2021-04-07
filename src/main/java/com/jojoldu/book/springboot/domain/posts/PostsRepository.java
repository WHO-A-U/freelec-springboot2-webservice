package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long>{
    //extends < [Entity class type] , [PK type] > 을 지정해주면 된다.
}
