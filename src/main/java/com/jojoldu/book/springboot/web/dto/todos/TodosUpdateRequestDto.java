package com.jojoldu.book.springboot.web.dto.todos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodosUpdateRequestDto {
    private String title;
    private String type;
    private int sequence;

    @Builder
    public TodosUpdateRequestDto(String title , String type,int sequence){
        this.title = title;
        this.type = type;
        this.sequence=sequence;
    }
}
