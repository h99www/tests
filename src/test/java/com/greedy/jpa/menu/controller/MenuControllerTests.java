package com.greedy.jpa.menu.controller;

import com.greedy.jpa.config.BeanConfigration;
import com.greedy.jpa.config.JpaApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {
        JpaApplication.class,
        BeanConfigration.class
})
class MenuControllerTests {
    @Autowired
    private MenuController menuController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
//        mockMvc =
    }

}