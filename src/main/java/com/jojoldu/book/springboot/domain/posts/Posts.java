package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter               // getter 와 인자가 없는 생성자를 만들어 준다.
@NoArgsConstructor    // lombok의 기능들
@Entity    //테이블과 링크될 클래스임을 나타낸다 JAP
public class Posts extends BaseTimeEntity { //시간을 자동으로 저장하기위해 BaseTimeEntity 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //PK 생성규칙을 나타냄
    private Long id;
    //PK를 이러한 형식으로 선언

    @Column( length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴클래스를 생성한다
    public Posts(String title, String content , String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //컬럼? 속성? 들에 대하여 어쩔수 없이 수정하는 메소드가 필요할 경우
    //setter라고 이름을 두지 않고 의미론적(semantic)으로 메소드명을 짓는다.
    public void update(String title , String content) {
        this.title = title;
        this.content = content;
    }

}
