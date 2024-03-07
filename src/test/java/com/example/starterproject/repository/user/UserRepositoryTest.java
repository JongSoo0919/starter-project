package com.example.starterproject.repository.user;

import com.example.starterproject.entity.user.Users;
import jakarta.persistence.EntityManager;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    private final UserRepository userRepository;

    public UserRepositoryTest(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저를 INSERT한다")
    void 유저적재() {
        //given
        Users user = Users.builder()
                .email("email")
                .name("name")
                .password("password")
                .role("ROLE_USER")
                .build();

        //when
        Users user1 = userRepository.save(user);

        //then
        Assertions.assertThat(user1).isEqualTo(user);
    }

    @Test
    @DisplayName("유저를 조회한다.")
    void 유저조회() {
        //given
        Users user = Users.builder()
                .email("email")
                .name("name")
                .password("password")
                .role("ROLE_USER")
                .build();
        Users user1 = userRepository.save(user);

        //when
        Users user2 = userRepository.findById(user1.getId())
                .orElse(Users.builder().build());

        //then
        System.out.println(user2.toString());
        Assertions.assertThat(user2).isEqualTo(user);
    }

    @Test
    @DisplayName("유저를 삭제한다.")
    void 유저삭제(){
        //given
        Users user = Users.builder()
                .email("email")
                .name("name")
                .password("password")
                .role("ROLE_USER")
                .build();
        userRepository.save(user);

        Assertions.assertThat(1).isEqualTo(userRepository.count());

        //when
        userRepository.deleteById(1L);

        //then
        Assertions.assertThat(0).isEqualTo(userRepository.count());
    }

    @Test
    @DisplayName("유저 패스워드를 변경한다.")
    void 패스워드_변경(){
        //given
        Users user = Users.builder()
                .email("email")
                .name("name")
                .password("password")
                .role("ROLE_USER")
                .build();
        Users saveUser = userRepository.save(user);

        //when
        Users user1 = userRepository.findById(saveUser.getId())
                .orElse(Users.builder().build());
        user1.updatePassword("newPassword");
        Users user2 = userRepository.findById(saveUser.getId())
                .orElse(Users.builder().build());

        //then
        Assertions.assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
    }
}