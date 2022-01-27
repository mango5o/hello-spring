package hello.hellospring.domain;


import javax.persistence.*;

@Entity
// JPA 가 관리하는 Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @ID = PK
    // @GeneratedValue : DB에 값을 넣으면  DB가 ID를 자동생성 해주는 것을 IDENTITY 라고 한다.
    private Long id;

    @Column(name="name") // DB에 있는 username 에 Mapping 된다.
    private String name;

    public Long getId() {
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
