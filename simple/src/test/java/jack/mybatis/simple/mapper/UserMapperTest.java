package jack.mybatis.simple.mapper;

import jack.mybatis.simple.model.SysRole;
import jack.mybatis.simple.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

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
}
