package com.jojoldu.book.springboot.web.dto.todos;

import com.jojoldu.book.springboot.domain.todos.Todos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodosSaveRequestDto {
    private String title;
    private String type;
    private int sequence;

    @Builder
    public TodosSaveRequestDto(String title , String type , int sequence){
        this.title = title;
        this.type = type;
        this.sequence = sequence;
    }

    public Todos toEntity(){
        return Todos.builder()
                .title(title)
                .type(type)
                .sequence(sequence)
                .build();
    }
}
