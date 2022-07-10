package hellojap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //필수 : jpa 인식을 위하여
@Table(name = "MEMBER") //db table name
public class Member {
    @Id //PK key javax.persistence
    private Long id;
    @Column(name="name") //column name
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
//Getter, Setter ...

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}