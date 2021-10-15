package kr.couchcoding.memo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
    // public User(){
    // } 만들어줌
public class User {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String password;

    @Builder
    public User(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
