package com.huel.xgms.admin.org.dao;

import com.huel.xgms.admin.org.bean.Dept;
import org.jfaster.mango.annotation.*;

import java.util.List;

@DB(
        table = "t_dept"
)
@Results({
        @Result(column = "n_id", property = "id"),
        @Result(column = "c_name", property = "name"),
        @Result(column = "c_code", property = "code"),
        @Result(column = "n_pid", property = "pid"),
        @Result(column = "n_sort", property = "sort"),
        @Result(column = "n_utype", property = "utype"),
        @Result(column = "n_utime", property = "utime")
})
public interface DeptDao {
	String COLUMNS = "n_id, c_name, c_code, n_pid, n_sort, n_utype, n_utime";

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ") values(:id, :name, :code, :pid, :sort, :utype, :utime)")
    int addDept(Dept dept);

    @SQL("select " + COLUMNS + " from #table where n_id = :1")
    Dept getDept(int id);

    @SQL("update #table set c_name=:name, n_sort=:sort, n_utime=:utime where n_id = :id")
    boolean updateDept(Dept dept);

    @SQL("update #table set n_utype=:1, n_utime=:2, c_code=null where n_id = :3")
    boolean deleteDept(int utype, long utime, int id);
    
    @SQL("select " + COLUMNS + " from #table where n_utype=:1 order by n_pid, n_sort")
    List<Dept> queryAllDepts(int utype);
    
    @SQL("select count(1) from #table where n_pid = :1")
    int queryChildCount(int pid);
    
    @SQL("select n_id, c_code from #table where n_utype=1 and (n_id=:1 or n_pid = :1) order by c_code")
    List<Dept> queryPcodeBcode(int pid);
}
