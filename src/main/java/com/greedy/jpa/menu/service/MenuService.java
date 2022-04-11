package com.greedy.jpa.menu.service;

import com.greedy.jpa.menu.dto.MenuDTO;
import com.greedy.jpa.menu.entity.Menu;
import com.greedy.jpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MenuService(MenuRepository repository, ModelMapper modelMapper) {

        this.menuRepository = repository;
        this.modelMapper = modelMapper;
    }

    public List<MenuDTO> findMenuList() {

        return menuRepository.findAllMenu(entityManager).stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

    public void registMenu(MenuDTO menu) {


            menuRepository.registMenu(entityManager, modelMapper.map(menu, Menu.class));

    }



}
