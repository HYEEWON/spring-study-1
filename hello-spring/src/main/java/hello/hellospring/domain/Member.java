package hello.hellospring.domain;

import javax.persistence.*;

@Entity //JPA가 관리하는 Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //기본키: 자동으로 생성 및 등록
    private Long id; // 시스템 내에서 저장되는 ID

    //@Column(name = "username") //DB 컬럼 이름 지정
    private String name;

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
