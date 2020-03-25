package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询所有操作
     * @return
     */
    List<Role> findAll();
}
