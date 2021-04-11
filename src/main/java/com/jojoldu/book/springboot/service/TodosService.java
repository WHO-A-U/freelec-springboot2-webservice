package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.todos.Todos;
import com.jojoldu.book.springboot.domain.todos.TodosRepository;
import com.jojoldu.book.springboot.web.dto.todos.TodosListResponseDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosResponseDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodosService {
    private final TodosRepository todosRepository;

    @Transactional
    public Long save (TodosSaveRequestDto requestDto){
        return todosRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id , TodosUpdateRequestDto requestDto){
        Todos todos = todosRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 리스트가 없습니다. id="+id));

        String type = todos.getType();
        switch (type){
            case "TODO":
                type = "DOING";
                break;
            case "DOING":
                type = "DONE";
                break;
            default:
                break;
        }
        todos.updateType(type);
        return id;
    }

    @Transactional
    public TodosResponseDto findById(Long id){
        Todos entity = todosRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 리스트 없음 id ="+id));

        return new TodosResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public HashMap<String, List<TodosListResponseDto>> findAllByType(){
        List<TodosListResponseDto> todoList = todosRepository.findAllByType("TODO").stream()
                .map(todos->new TodosListResponseDto(todos))
                .collect(Collectors.toList());

        List<TodosListResponseDto> doingList = todosRepository.findAllByType("DOING").stream()
                .map(todos->new TodosListResponseDto(todos))
                .collect(Collectors.toList());

        List<TodosListResponseDto> doneList = todosRepository.findAllByType("DONE").stream()
                .map(todos->new TodosListResponseDto(todos))
                .collect(Collectors.toList());

        HashMap<String,List<TodosListResponseDto>> map = new HashMap<>();
        map.put("TODO",todoList);
        map.put("DOING",doingList);
        map.put("DONE",doneList);
        return map;
    }

}
