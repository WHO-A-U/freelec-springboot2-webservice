package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass    //JAP ENtity 클래스들이 현재 이 클래스를 상속할 경우 여기서 선언한 column 속성들을 사용할수 있게 함
@EntityListeners(AuditingEntityListener.class) //이 클래스에 Auditing 기능을 포함
public class BaseTimeEntity {

    @CreatedDate //entity가 생성될때 시간이 자동으로 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate //entity가 변경될때 시간이 자동으로 저장됨
    private LocalDateTime modifiedDate;


}
