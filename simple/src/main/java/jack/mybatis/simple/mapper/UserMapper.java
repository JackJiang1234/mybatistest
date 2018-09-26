package jack.mybatis.simple.mapper;

import jack.mybatis.simple.model.SysUser;
import jack.mybatis.simple.model.SysRole;

import java.util.List;

public interface UserMapper {
    /**
     * query use id
     *
     * @param id
     * @return
     * */

    SysUser selectById(Long id);

    /**
     * get all users
     * */
    List<SysUser> selectAll();

    /**
     * get user roles
     *
     * @param userid
     * @return
     * */
    List<SysRole> selectRolesByUserId(Long userid);
}
