package com.jojoldu.book.springboot.domain.todos;


import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Todos extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(length = 500,nullable = false)
    private int sequence;

    @Column(length = 500,nullable = false)
    private String type;

    @Builder
    public Todos(String title , int sequence , String type){
        this.title = title;
        this.sequence = sequence;
        this.type = type;
    }

    public void updateType(String type){
        this.type = type;
    }
}
