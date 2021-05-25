package com.toy.user.dto;



import com.toy.user.domain.Role;
import com.toy.user.domain.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {

    private String name;
    private String email;
    private String picture;
    private Role role;
    private String delFlag;

    public UserRequestDto(String name, String email, String picture, Role role, String delFlag){

        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.delFlag = delFlag;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .picture(picture)
                .role(role)
                .email(email)
                .build();
    }

}
