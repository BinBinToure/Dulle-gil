package com.introduce.seoulgil.dto;


import com.introduce.seoulgil.domain.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String password;
    private String auth;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .auth(auth)
                .build();
    }

    @Builder
    public MemberDto(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }
}
