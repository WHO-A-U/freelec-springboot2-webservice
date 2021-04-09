package com.jojoldu.book.springboot.web.dto.todos;

import com.jojoldu.book.springboot.domain.todos.Todos;
import lombok.Getter;

@Getter
public class TodosResponseDto {
    private final Long id;
    private final String title;
    private final String type;
    private final String sequence;

    public TodosResponseDto(Todos entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.type = entity.getType();
        this.sequence = entity.getSequence();
    }
}
