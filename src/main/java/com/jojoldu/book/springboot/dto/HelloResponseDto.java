package com.jojoldu.book.springboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//선언된 모든 final 필드나 @NonNull 이포함된 필드에 대해서서생성자를 생성해 줍니다.
//
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

