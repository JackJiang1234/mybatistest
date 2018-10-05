package jack.mybatis.simple.mapper;

import jack.mybatis.simple.model.SysRole;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.annotations.CacheNamespaceRef;

import java.util.List;

@CacheNamespaceRef(RoleMapper.class )
public interface RoleMapper {

    @Select({"select id,role_name roleName, enabled, create_by createBy, create_time createTime",
            "from sys_role",
            "where id = #{id}"})
    SysRole selectById(Long id);

    SysRole selectById2(Long id);

    List<SysRole> selectAll();

    List<SysRole> selectAll(RowBounds rowBounds);

    int insert(SysRole sysRole);

    int insert2(SysRole sysRole);

    int insert3(SysRole sysRole);

    @Update({"update sys_role",
            "set role_name = #{roleName},",
            "enabled = #{enabled},",
            "create_by = #{createBy},",
            "create_time = #{createTime, jdbcType=TIMESTAMP}",
            "where id = #{id}"
    })
    int updateById(SysRole sysRole);


    int deleteById(Long id);

    /**
     * 获取所有角色和对应的权限信息
     *
     * @return
     */
    List<SysRole> selectAllRoleAndPrivileges();

    /**
     * 根据用户 ID 获取用户的角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserIdChoose(Long userId);
}
