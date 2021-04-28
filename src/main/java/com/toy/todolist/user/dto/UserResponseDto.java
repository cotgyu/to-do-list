package com.toy.todolist.user.dto;


import com.toy.todolist.user.domain.Role;
import com.toy.todolist.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    private String delFlag;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole();
        this.delFlag = user.getDelFlag();

    }

}
