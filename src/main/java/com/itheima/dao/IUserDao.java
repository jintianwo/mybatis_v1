package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 插入用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新操作
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除操作
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 根据id 查询用户信息
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据用户名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总条数
     * @param id
     * @return
     */
    Integer findTotal(String id);

    /**
     * 根据条件查询
     * @param vo
     * @return
     */
    List<User> findUserByQuery(QueryVo vo);
}
