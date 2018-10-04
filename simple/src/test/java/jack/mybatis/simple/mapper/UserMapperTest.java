package jack.mybatis.simple.mapper;

import jack.mybatis.simple.model.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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
        } finally {
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
        } finally {
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
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@jack.com");

            int result = mapper.updateById(user);
            Assert.assertEquals(1, result);
            user = mapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = mapper.selectById(1L);
            Assert.assertNotNull(user);

            Assert.assertEquals(1, mapper.deleteById(user));
            Assert.assertNull(mapper.selectById(1L));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles = mapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> users = mapper.selectByUser(query);
            Assert.assertTrue(users.size() > 0);

            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            users = mapper.selectByUser(query);
            Assert.assertTrue(users.size() > 0);

            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            users = mapper.selectByUser(query);
            Assert.assertTrue(users.size() == 0);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUserEmail("test@mybatis.tk");
            int result = mapper.updateByIdSelective(user);
            Assert.assertEquals(1, result);

            user = mapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectbyIdOrUserName() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");

            SysUser user = mapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);

            query.setId(null);
            user = mapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);

            query.setUserName(null);
            user = mapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2, userList.size());
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            List<SysUser> userList = new ArrayList<SysUser>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            //将新建的对象批量插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);
            for (SysUser user : userList) {
                System.out.println(user.getId());
            }
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            //可以用 selectUserAndRoleById2 替换进行测试
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            //user 不为空
            Assert.assertNotNull(user);
            //user.role 也不为空
            Assert.assertNotNull(user.getRole());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            //可以用 selectUserAndRoleById2 替换进行测试
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            //user 不为空
            Assert.assertNotNull(user);
            //user.role 也不为空
            Assert.assertNotNull(user.getRole());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            //user 不为空
            Assert.assertNotNull(user);
            //user.role 也不为空

            //lazyLoadTriggerMethods
            System.out.println("调用 user.equals(null)");
            user.equals(null);
            System.out.println("调用 user.getRole()");
            Assert.assertNotNull(user.getRole());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAllUserAndRoles(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数：" + userList.size());
            for(SysUser user : userList){
                System.out.println("用户名：" + user.getUserName());
                for(SysRole role: user.getRoleList()){
                    System.out.println("角色名：" + role.getRoleName());
                    for(SysPrivilege privilege : role.getPrivilegeList()){
                        System.out.println("权限名：" + privilege.getPrivilegeName());
                    }
                }
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("用户名：" + user.getUserName());
            System.out.println("email:" + user.getUserEmail());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    private SysUser createUser() {
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
