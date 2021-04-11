package com.jojoldu.book.springboot.web.dto.todos;

import com.jojoldu.book.springboot.domain.todos.Todos;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TodosListResponseDto {
    private final Long id;
    private final String title;
    private final String type;
    private final int sequence;

    public TodosListResponseDto(Todos entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.type = entity.getType();
        this.sequence = entity.getSequence();
    }

}
