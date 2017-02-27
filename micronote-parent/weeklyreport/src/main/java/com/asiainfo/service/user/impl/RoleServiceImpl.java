package com.asiainfo.service.user.impl;

import com.asiainfo.domain.entity.user.Role;
import com.asiainfo.repository.user.RoleRepository;
import com.asiainfo.service.user.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eason on 2017/2/21.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<?> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }
}
