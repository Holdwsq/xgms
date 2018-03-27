package com.huel.xgms.admin.system.dao;

import com.huel.xgms.admin.system.bean.Function;
import org.jfaster.mango.annotation.*;

import java.util.List;

@DB(
        table = "t_sys_func"
)
@Results({
        @Result(column = "n_id", property = "id"),
        @Result(column = "c_name", property = "name"),
        @Result(column = "c_icon", property = "icon"),
        @Result(column = "n_pid", property = "pid"),
        @Result(column = "n_order", property = "order"),
        @Result(column = "c_url", property = "url"),
        @Result(column = "c_permission", property = "permission")
})
public interface FunctionDao {
    String COLUMNS = "n_id, c_name, c_icon, n_pid, n_order, c_url, c_permission";

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ") values(:id, :name, :icon, :pid, :order, :url, :permission)")
    int addFunction(Function function);

    @SQL("select " + COLUMNS + " from #table where n_id = :1")
    Function getFunction(int id);
    
    @SQL("select f.n_id, f.c_name, f.c_icon, f.n_pid, f.n_order, f.c_url, f.c_permission " +
    		"from t_sys_user_role ur " +
    		"join t_sys_role_func rf on ur.n_role_id = rf.n_role_id " +
    		"join t_sys_func f on rf.n_func_id = f.n_id " +
    		"where ur.n_user_id = :1 " +
    		"order by n_pid asc, n_order asc")
    List<Function> queryFuncsByUserId(int userid);
    
    @SQL("select f.n_id, f.c_name, f.c_icon, f.n_pid, f.n_order, f.c_url, f.c_permission " +
    		"from t_sys_func f where f.n_pid=:1")
    List<Function> queryFuncsByPid(int pid);
    
    @SQL("select f.c_permission from t_sys_user_role ur " +
    		"join t_sys_role_func rf on ur.n_role_id = rf.n_role_id " +
    		"join t_sys_func f on rf.n_func_id = f.n_id " +
    		"where ur.n_user_id = :1 and f.c_permission is not null order by n_pid asc, n_order asc")
    List<String> queryPermissionByUserId(int userId);

    @SQL("update #table set c_name=:name, c_icon=:icon, n_pid=:pid, n_order=:order, c_url=:url, c_permission=:permission where n_id = :id")
    boolean updateFunction(Function function);

    @SQL("delete from #table where n_id = :1")
    boolean deleteFunction(int id);
    
    @SQL("select " + COLUMNS + " from #table order by n_pid asc, n_order asc")
    List<Function> queryAll();
}