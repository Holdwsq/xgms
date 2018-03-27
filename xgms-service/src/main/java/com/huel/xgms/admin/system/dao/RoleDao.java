package com.huel.xgms.admin.system.dao;

import com.huel.xgms.admin.system.bean.Role;
import com.huel.xgms.admin.system.bean.RoleFunc;
import org.jfaster.mango.annotation.*;

import java.util.List;

@DB(
        table = "t_sys_role"
)
@Results({
        @Result(column = "n_id", property = "id"),
        @Result(column = "c_name", property = "name")
})
public interface RoleDao {
    String COLUMNS = "n_id, c_name";

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ") values(:id, :name)")
    int addRole(Role role);
    
    @SQL("insert into t_sys_role_func(n_role_id, n_func_id) values(:1.roleId, :1.funcId)")
    void addRoleFuncs(List<RoleFunc> list);

    @SQL("select " + COLUMNS + " from #table where n_id = :1")
    Role getRole(int id);

    @SQL("update #table set c_name=:name where n_id = :id")
    boolean updateRole(Role role);

    @SQL("delete from #table where n_id = :1")
    boolean deleteRole(int id);
    
    @SQL("delete from t_sys_role_func where n_role_id = :1")
    void deleteRoleFunc(int roleId);
    
    @SQL("select " + COLUMNS + " from #table")
    List<Role> list();
    
    @SQL("select n_func_id from t_sys_role_func where n_role_id = :1")
    List<Integer> getRoleFunc(int roleId);
}