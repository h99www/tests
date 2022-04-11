package com.greedy.jpa.menu.service;

import com.greedy.jpa.config.BeanConfigration;
import com.greedy.jpa.config.JpaApplication;
import com.greedy.jpa.menu.dto.MenuDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {
        JpaApplication.class, BeanConfigration.class
})
class MenuServiceTests {

    @Autowired
    private MenuService service;

    @Test
    public void initTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("메뉴 전체조회 테스트")
    public void findAllMenuList_test() {

        List<MenuDTO> menuList = service.findMenuList();

        assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }

    @Test
    @DisplayName("메뉴 생성 테스트")
    @Transactional
    public void registMenu_test() throws Exception {

        MenuDTO menu = new MenuDTO(0,"1",1,10,"Y");

        assertDoesNotThrow(() -> {
            service.registMenu(menu);
        });
    }


}