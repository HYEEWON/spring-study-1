package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
@JsonFilter("UserInfo") //Filter의 이름
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 두 글자 이상 입력해 주세요.")
    private String name;

    @Past
    private Date joinDate;

    //@JsonIgnore //데이터를 숨김
    private String password;

    //@JsonIgnore
    private String ssn;
}
