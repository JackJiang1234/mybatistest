package jack.mybatis.simple.mapper;

import jack.mybatis.simple.model.SysRole;
import jack.mybatis.simple.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectedById() {
        try (SqlSession sqlSession = this.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1L);

            Assert.assertEquals("admin", user.getUserName());
        }
    }

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = this.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> users = mapper.selectAll();

            Assert.assertNotNull(users);
            Assert.assertTrue(users.size() > 0);
        }
    }

    @Test
    public void testSelectUserRoles() {
        try (SqlSession sqlSession = this.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> userRoles = mapper.selectRolesByUserId(1L);

            Assert.assertNotNull(userRoles);
            Assert.assertTrue(userRoles.size() > 0);
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = createUser();
            int result = mapper.insert(user);
            Assert.assertEquals(1, result);
            Assert.assertNull(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = createUser();
            int result = mapper.insert2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());

            System.out.println(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = createUser();
            int result = mapper.insert3(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());

            System.out.println(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = this.getSqlSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@jack.com");

            int result = mapper.updateById(user);
            Assert.assertEquals(1, result);
            user = mapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = this.getSqlSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1L);
            Assert.assertNotNull(user);

            Assert.assertEquals(1, mapper.deleteById(user));
            Assert.assertNull(mapper.selectById(1L));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = this.getSqlSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles = mapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size() > 0);
        }finally {
            sqlSession.close();
        }
    }

    private SysUser createUser(){
        SysUser user = new SysUser();
        user.setUserName("jack");
        user.setUserPassword("123456");
        user.setUserEmail("test@mybatis.jack");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(new Date());

        return user;
    }
}
