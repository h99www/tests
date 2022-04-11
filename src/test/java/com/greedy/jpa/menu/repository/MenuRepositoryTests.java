package com.greedy.jpa.menu.repository;

import com.greedy.jpa.config.BeanConfigration;
import com.greedy.jpa.config.JpaApplication;
import com.greedy.jpa.menu.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {
        BeanConfigration.class, JpaApplication.class
})
class MenuRepositoryTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MenuRepository repo;

    @Test
    public void initManager_Test() {

        assertNotNull(entityManager);
        assertNotNull(repo);
    }

    @Test
    public void 전체_메뉴_조회_테스트() {

        List<Menu> menuList = repo.findAllMenu(entityManager);

        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }
    
    @Test
    public void 하나의_메뉴코드로_메뉴정보_조회_테스트() {
        int menuCode = 1;
        Menu menuInfo = repo.findMenuByCode(entityManager, menuCode);
        assertNotNull(menuInfo);
        System.out.println("menuInfo = " + menuInfo);
    }

    @Test
    @Transactional
    public void 메뉴생성테스트() {
        Menu menu = new Menu();
        menu.setMenuName("안녕");
        menu.setMenuPrice(10000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

//        entityManager.persist(menu);
        repo.registMenu(entityManager, menu);

        Menu compareMenu = entityManager.find(Menu.class, menu.getMenuCode());

        assertEquals(menu.getMenuCode(), compareMenu.getMenuCode());
        assertEquals(menu.getMenuName(), compareMenu.getMenuName());
        assertEquals(menu.getMenuPrice(), compareMenu.getMenuPrice());
        assertEquals(menu.getCategoryCode(), compareMenu.getCategoryCode());
        assertEquals(menu.getOrderableStatus(), compareMenu.getOrderableStatus());

    }

    @Test
    @Transactional
    public void 메뉴수정테스트() {
        /* 테스트 시나리오
        * 1. 1번 메뉴 originMenu에 조회 후 detach
        * 2. 1번 메뉴 modifyMenu에 조회
        * 3. modifyMenu 값 변경 후 persist
        * 4. 1번메뉴 modifiedMenu에 조회
        * 5. originMenu(기존값)과 modifiedMenu(수정 후 persist 날린값)과 다른지 검증
        * */
        /* 1 */
        int menuCode = 1;
        Menu originMenu = entityManager.find(Menu.class, menuCode);
        entityManager.detach(originMenu);

        /* 2 */
        Menu modifyMenu = entityManager.find(Menu.class, menuCode);

        /* 3 */
        modifyMenu.setMenuPrice(modifyMenu.getMenuPrice() + 10000);
        modifyMenu.setMenuName(modifyMenu.getMenuName() + "123");
        modifyMenu.setOrderableStatus("Y".equals(modifyMenu.getOrderableStatus()) ? "N": "Y");
        modifyMenu.setCategoryCode(modifyMenu.getCategoryCode() == 1 ? 1 : 2);
        entityManager.persist(modifyMenu);

        /* 4 */
        Menu modifiedMenu = entityManager.find(Menu.class, menuCode);

        /* 5 */
        assertNotEquals(originMenu.getMenuName(), modifiedMenu.getMenuName());
        assertNotEquals(originMenu.getMenuPrice(), modifiedMenu.getMenuPrice());
        assertNotEquals(originMenu.getCategoryCode(), modifiedMenu.getCategoryCode());
        assertNotEquals(originMenu.getOrderableStatus(), modifiedMenu.getOrderableStatus());
    }
}





























