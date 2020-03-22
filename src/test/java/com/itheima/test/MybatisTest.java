package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * mybatis的入门案例
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        //1.读取配置文件
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception{

        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * 保持操作
     */
    @Test
    public void testSave() throws Exception{
        User user = new User();
        user.setUsername("李布盛");
        user.setAddress("光熙门北路");
        user.setSex("男");
        user.setBirthday(new Date());
       userDao.saveUser(user);

    }

    /**
     * 更新操作
     */
    @Test
    public void Update() throws Exception{
        User user = new User();
        user.setId(50);
        user.setUsername("杨幂");
        user.setAddress("西坝河北路");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.updateUser(user);

    }

    /**
     * 更新操作
     */
    @Test
    public void delete() throws Exception{

        userDao.deleteUser(50);

    }

    /**
     * 根据id 查询一个
     */
    @Test
    public void findById() throws Exception{

        User user = userDao.findById(53);

        System.out.println(user);

    }

    /**
     * 模糊查询
     */
    @Test
    public void findByName() throws Exception{

        List<User> users = userDao.findByName("%王%");

        for(User user : users){
            System.out.println(user);
        }

    }

    /**
     * 查询总条数
     */
    @Test
    public void findTotal() throws Exception{

        Integer sum = userDao.findTotal("id");

        System.out.println(sum);
    }

    /**
     * 测试使用queryVo作为查询条件
     */
    @Test
    public void findByVo() throws Exception{

        QueryVo queryVo = new QueryVo();

        User user = new User();

        user.setUsername("%王%");

        queryVo.setUser(user);

        List<User> users = userDao.findByName("%王%");

        for(User U : users){
            System.out.println(U);
        }

    }
}
