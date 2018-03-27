package com.huel.xgms.admin.org.dao;

import com.huel.xgms.admin.org.bean.Emploee;
import org.jfaster.mango.annotation.*;
import org.jfaster.mango.plugin.page.Page;

import java.util.List;

@DB(
        table = "t_emploee"
)
@Results({
        @Result(column = "n_id", property = "id"),
        @Result(column = "c_name", property = "name"),
        @Result(column = "c_py", property = "py"),
        @Result(column = "c_emplid", property = "emplid"),
        @Result(column = "c_tel", property = "tel"),
        @Result(column = "c_mobile", property = "mobile"),
        @Result(column = "n_deptid", property = "deptid"),
        @Result(column = "c_job", property = "job"),
        @Result(column = "n_sort", property = "sort"),
        @Result(column = "n_utype", property = "utype"),
        @Result(column = "n_utime", property = "utime"),
        @Result(column = "c_avater", property = "avater"),
        @Result(column = "n_entry_time", property = "entryTime"),
        @Result(column = "n_quit_time", property = "quitTime"),
        @Result(column = "c_email", property = "email"),
        @Result(column = "c_pwd", property = "pwd"),
        @Result(column = "deptName", property = "deptName")
})
public interface EmploeeDao {
	String COLUMNS = "n_id, c_name, c_py, c_emplid, c_tel, c_mobile, n_deptid, c_job, n_sort, n_utype, n_utime, c_avater, n_entry_time, n_quit_time, c_email";

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ", c_pwd) values(:id, :name, :py, :emplid, :tel, :mobile, :deptid, :job, :sort, :utype, :utime, :avater, :entryTime, :quitTime, :email, :pwd)")
    int addEmploee(Emploee emploee);

    @SQL("select " + COLUMNS + " from #table where n_id = :1")
    Emploee getEmploee(int id);
    
    @SQL("select " + COLUMNS + " from #table where c_emplid = :1 and c_pwd = :2")
    Emploee getEmploee(String emplid, String pwd);

    @SQL("update #table set c_name=:name, c_py=:py, c_emplid=:emplid, c_tel=:tel, c_mobile=:mobile, n_deptid=:deptid, c_job=:job, n_sort=:sort, n_utype=:utype, n_utime=:utime, c_avater=:avater, n_entry_time=:entryTime, n_quit_time=:quitTime, c_email=:email where n_id = :id")
    boolean updateEmploee(Emploee emploee);

    @SQL("delete from #table where n_id = :1")
    boolean deleteEmploee(int id);
    
    @SQL("select e.n_id, e.c_name, c_py, c_emplid, c_tel, c_mobile, " +
    		"n_deptid, c_job, e.n_sort, e.n_utype, e.n_utime, " +
    		"c_avater, n_entry_time, n_quit_time, c_email, " +
    		"d.c_name AS deptName from #table e " +
    		"left join t_dept d on e.n_deptid = d.n_id " +
    		"where n_quit_time = 0 || n_quit_time > :1 order by n_sort")
    List<Emploee> queryNormalEmplByQuitTime(long quitTime);
    
    @SQL("select count(1) from #table where n_deptid = :1")
    int queryCountOfDept(int deptId);
    
    @SQL("select e.n_id, e.c_name, c_py, c_emplid, c_tel, c_mobile, " +
    		"n_deptid, c_job, e.n_sort, e.n_utype, e.n_utime, " +
    		"c_avater, n_entry_time, n_quit_time, c_email, " +
    		"d.c_name AS deptName from #table e " +
    		"left join t_dept d on e.n_deptid = d.n_id " +
    		"order by d.n_pid, d.n_sort, e.n_sort ")
    List<Emploee> query(Page page);
}
