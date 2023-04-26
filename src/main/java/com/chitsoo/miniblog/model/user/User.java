package com.chitsoo.miniblog.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // @AllArgsConstructor 이게 있어야만 붙일 수 있다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //hibernate가 나중에 객체 new 해주려고. protected를 건 이유는 개발자가 직접 new 하지 말라고.
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20) // uk(unique)가 붙어있음
    private String username;
    @Column(length = 60) // 실제로는 20자이하만 받을 예정 - 암호화 될 때는 60자(Byte)로 바뀜.
    private String password;
    @Column(length = 50)
    private String email;
    private String role; // USER(고객)
    private String profile; // 유저 프로필 사진의 경로
    private Boolean status; // 활성, 비활성
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime updatedAt;

    // 프로필 사진 변경
    public void changeProfile(String profile) {
        this.profile = profile;
    }

    // 회원정보 수정
    public void update(String password, String email) {
        this.password = password;
        this.email = email;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

