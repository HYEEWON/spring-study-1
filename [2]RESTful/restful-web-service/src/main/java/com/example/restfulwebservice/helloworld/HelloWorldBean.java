package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok
@Data //set,get 등을 자동 생성
@AllArgsConstructor // 생성자 자동 생성
@NoArgsConstructor // 디폴트 생성자 자동 생성
public class HelloWorldBean {
    private String message;
}
