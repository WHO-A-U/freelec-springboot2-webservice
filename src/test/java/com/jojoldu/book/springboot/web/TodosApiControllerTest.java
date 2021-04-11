package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.todos.Todos;
import com.jojoldu.book.springboot.domain.todos.TodosRepository;
import com.jojoldu.book.springboot.service.TodosService;
import com.jojoldu.book.springboot.web.dto.todos.TodosListResponseDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.todos.TodosUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodosApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodosRepository todosRepository;

    @Autowired
    private TodosService todosService;

    @After
    public void tearDown() throws Exception{
        todosRepository.deleteAll();
    }

    @Test
    public void save_todos_test () throws Exception{

        String title = "title";
        String type = "type";
        int sequence = 1;

        TodosSaveRequestDto requestDto = TodosSaveRequestDto.builder()
                .title(title)
                .type(type)
                .sequence(sequence)
                .build();

        String url = "http://localhost:"+port+"/api/v1/todos";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Todos> all = todosRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getType()).isEqualTo(type);
        assertThat(all.get(0).getSequence()).isEqualTo(sequence);

    }

    @Test
    public void update_todo_test() throws Exception{
        String title="title";
        String type="DOING";
        int sequence=1;

        Todos savedTodos = todosRepository.save(
                Todos.builder()
                .title(title)
                .type(type)
                .sequence(sequence)
                .build());

        Long updateTodoId = savedTodos.getId();
        String expectedTitle = "title";
        String expectedType = "DONE";
        int expectedSequence = 1;

        TodosUpdateRequestDto requestDto = TodosUpdateRequestDto.builder()
                .title(expectedTitle)
                .type(type)
                .sequence(sequence)
                .build();


        String url = "http://localhost:"+port+"api/v1/todos/"+updateTodoId;

        HttpEntity<TodosUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);



        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT,requestEntity,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Todos> all = todosRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getType()).isEqualTo(expectedType);
        assertThat(all.get(0).getSequence()).isEqualTo(expectedSequence);

    }

    @Test
    public void get_todos_test() throws Exception{
        String title1="title";
        String type1="DOING";
        int sequence1=1;

        Todos savedTodos1 = todosRepository.save(
                Todos.builder()
                        .title(title1)
                        .type(type1)
                        .sequence(sequence1)
                        .build());

        String title2="title";
        String type2="DONE";
        int sequence2=1;

        Todos savedTodos2 = todosRepository.save(
                Todos.builder()
                        .title(title2)
                        .type(type2)
                        .sequence(sequence2)
                        .build());

        HashMap<String,List<TodosListResponseDto>> map = todosService.findAllByType();

        List<TodosListResponseDto> todoList = map.get("TODO");
        List<TodosListResponseDto> doingList = map.get("DOING");
        List<TodosListResponseDto> doneList = map.get("DONE");

        for(TodosListResponseDto x : todoList){
            System.out.println(x);

        }
        for(TodosListResponseDto x : doingList){
            System.out.println(x);
        }
        for(TodosListResponseDto x : doneList){
            System.out.println(x);
        }
    }
}
