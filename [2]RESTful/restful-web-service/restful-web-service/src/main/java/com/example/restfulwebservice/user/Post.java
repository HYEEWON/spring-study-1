package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // User : Post -> 1 : N // Main : Sub -> Parent : Child
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩: 처음에는 프록시 객체로 가져옴
    // 실제로 데이터를 사용하는 시점에 초기화 -> DB 쿼리 사용
    @JsonIgnore
    private User user;
}
