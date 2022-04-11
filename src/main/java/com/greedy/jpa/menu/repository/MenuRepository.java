package com.greedy.jpa.menu.repository;

import com.greedy.jpa.menu.entity.Menu;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
/*dsfadsaf*/
@Repository
public class MenuRepository {

    public List<Menu> findAllMenu(EntityManager entityManager) {
        String jpql = "SELECT m FROM Menu m ORDER BY m.menuCode ASC";
        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }

    public Menu findMenuByCode(EntityManager entityManager, int menuCode) {

        return entityManager.find(Menu.class, menuCode);
    }

    @Transactional
    public void registMenu(EntityManager entityManager, Menu menu) {

        entityManager.persist(menu);
    }

    @Transactional
    public void modifyMenu(EntityManager entityManager, Menu menu) {

    }

}
