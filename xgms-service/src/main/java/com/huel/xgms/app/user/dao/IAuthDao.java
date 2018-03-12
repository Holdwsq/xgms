package com.huel.xgms.app.user.dao;

import com.huel.xgms.app.user.bean.AuthBean;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;
import org.springframework.test.context.jdbc.Sql;

/**
 * 学生认证持久层操作
 * @author wsq
 * @date 2018/3/12
 */
@DB(
        table = "t_xgms_auth"
)
@Results({
        @Result(column = "c_id", property = "id"),
        @Result(column = "c_user_id", property = "userId"),
        @Result(column = "c_user_name", property = "userName"),
        @Result(column = "c_school_name", property = "schoolName"),
        @Result(column = "c_education", property = "education"),
        @Result(column = "c_student_no", property = "studentNo"),
        @Result(column = "c_entrance_year", property = "entranceYear"),
        @Result(column = "c_operator_id", property = "operatorId"),
        @Result(column = "l_operate_time", property = "operateTime"),
        @Result(column = "l_create_time", property = "createTime"),
        @Result(column = "c_delete_flag", property = "deleteFlag"),
        @Result(column = "c_auth_status", property = "authStatus")
})
public interface IAuthDao {
    String ALL_COLUMNS = "c_id, c_user_id, c_user_name, c_school_name, c_education, c_student_no, "
            + " c_entrance_year, c_operator_id, l_operate_time, l_create_time, c_delete_flag, c_auth_status";
    /**
     * 用户学生认证
     * @param bean
     */
    @SQL("INSERT INTO #table (" + ALL_COLUMNS + ")"
            + " VALUES "
            + " (:1.id, :1.userId, :1.userName, :1.schoolName, :1.education, :1.studentNo, :1.entranceYear,"
            + " :1.operatorId, :1.operateTime, :1.createTime, :1.deleteFlag, :1.authStatus)")
    void save(AuthBean bean);

    /**
     * 获取用户认证状态
     * @param userId
     * @return authStatus
     */
    @SQL("SELECT t.c_auth_status from #table t where t.c_user_id = :1 "
            + " GROUP BY t.c_user_id HAVING MAX(t.l_create_time)")
    String getAuthStatus(String userId);
}
