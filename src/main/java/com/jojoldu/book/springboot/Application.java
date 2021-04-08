package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Audit 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
        //SpringApplication을 통해 외부 톰캣(WAS)를 사용하지 않고 내부 WAS를 실행
        //gradle에 잘보면 Dependencies 에 tomcat이 존재함
    }
}
