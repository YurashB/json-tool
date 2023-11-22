package com.jsontool;

import com.jsontool.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonToolApplicationTests {

    @Test
    void contextLoads() {
        User user = new User("login", "email", "password");
    }

}
