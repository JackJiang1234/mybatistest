package jack.mybatis.simple.mapper;

import jack.mybatis.simple.model.SysUser;
import jack.mybatis.simple.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * query use id
     *
     * @param id
     * @return
     */

    SysUser selectById(Long id);

    /**
     * get all users
     */
    List<SysUser> selectAll();

    /**
     * get user roles
     *
     * @param userid
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userid);

    /**
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * update by key
     *
     * @param sysUser
     * @return
     * */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(SysUser sysUser);

    /**
     * 根据用户 id 和 角色的 enabled 状态获取用户的角色
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId")Long userId, @Param("enabled")Integer enabled);

    /**
     * @param user
     * @return
     * */
    List<SysUser> selectByUser(SysUser user);

    int updateByIdSelective(SysUser sysUser);
}
