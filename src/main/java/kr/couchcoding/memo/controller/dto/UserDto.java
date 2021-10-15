package kr.couchcoding.memo.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String password;

    public UserDto(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
