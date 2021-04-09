package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.TodosService;
import com.jojoldu.book.springboot.web.dto.todos.TodosResponseDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TodosApiController {

    private final TodosService todosService;

    @PostMapping("/api/v1/todos")
    public Long save(@RequestBody TodosSaveRequestDto requestDto){
        return todosService.save(requestDto);
    }

    @GetMapping("/api/v1/todos/{id}")
    public TodosResponseDto findById (@PathVariable Long id){
        return todosService.findById(id);
    }

    @PutMapping("api/v1/todos/{id}")
    public Long update(@PathVariable Long id , @RequestBody TodosUpdateRequestDto requestDto){
        return todosService.update(id,requestDto);
    }


}
