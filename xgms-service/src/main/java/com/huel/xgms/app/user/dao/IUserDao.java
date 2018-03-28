package com.huel.xgms.app.user.dao;

import com.huel.xgms.app.user.bean.AuthBean;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.base.bean.PagingQueryBean;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.plugin.page.Page;

import java.util.List;
import java.util.Set;

/**
 * 用户持久层接口
 * @author wsq
 * @date 2018/3/4
 */
@DB(
        table = "t_xgms_user"
)
@Results({
        @Result(column = "c_id", property = "id"),
        @Result(column = "c_name", property = "userName"),
        @Result(column = "c_hobby", property = "hobby"),
        @Result(column = "c_preview_id", property = "previewId"),
        @Result(column = "c_file_id", property = "fileId"),
        @Result(column = "c_sex", property = "sex"),
        @Result(column = "d_birthday", property = "birthday"),
        @Result(column = "c_brief", property = "brief"),
        @Result(column = "c_school", property = "school"),
        @Result(column = "c_delete_flag", property = "deleteFlag"),
        @Result(column = "n_create_time", property = "createTime"),
        @Result(column = "n_update_time", property = "updateTime"),
        @Result(column = "n_age", property = "age"),
        @Result(column = "c_phone", property = "phone"),
        @Result(column = "c_auth", property = "auth"),
        @Result(column = "c_account_name", property = "accountName"),
        @Result(column = "c_pwd", property = "pwd")
})
public interface IUserDao {

    String COLUMNS = "c_name, c_hobby, c_preview_id, c_file_id, c_sex, d_birthday, c_brief, c_school, c_delete_flag, "
            + "n_create_time, n_update_time, c_phone, c_auth, c_account_name, c_pwd, n_age";
    String ALL_COLUMNS = "c_id, " + COLUMNS;

    /**
     * 获取对应ID的用户详情信息
     * @param userId 用户ID
     * @return {@link User}
     */
    @SQL("SELECT " + ALL_COLUMNS + " FROM #table t"
            + " where t.c_delete_flag = " + User.FLAG_DELETE_NO
            + " AND t.c_id = :1")
    User getUserInfo(String userId);

    /**
     *  更新用户资料
     * @param user
     */
    @SQL("UPDATE #table t SET t.c_name = :1.userName, t.c_hobby = :1.hobby, t.c_preview_id = :1.previewId, t.c_file_id = :1.fileId, "
            + " t.c_sex = :1.sex, t.d_birthday = :1.birthday, t.c_brief = :1.brief, t.c_school = :1.school, t.n_update_time = :1.updateTime, "
            + " t.n_age = :1.age, t.c_phone = :1.phone "
            + " WHERE t.id = :1.id")
    void updateUserInfo(User user);

    /**
     * 获取用户列表
     * @param userIds 用户id列表
     * @return List<User>
     */
    @SQL("SELECT " + ALL_COLUMNS + " FROM #table t"
            + " WHERE t.c_delete_flag = " + User.FLAG_DELETE_NO
            + " AND t.c_id IN (:1)")
    List<User> list(Set<String> userIds);

    /**
     * 分页获取用户信息
     * @param queryBean
     * @param page
     * @return
     */
    @SQL("SELECT " + ALL_COLUMNS + " FROM #table t"
            + " WHERE t.c_delete_flag = " + User.FLAG_DELETE_NO
            + " #if(:1.id != null) and t.c_id = :1.id #end "
            + " #if(:1.state != null) and t.c_auth = :1.state #end "
            + " #if(:1.key != null) and t.c_account_name like '%'||:1.key||'%' #end "
            + " #if(:1.beginTime != null) and t.n_create_time >= :1.beginTime #end "
            + " #if(:1.endTime != null) and t.n_create_time <= :1.endTime #end "
            + " ORDER BY t.n_create_time DESC")
    List<User> listByPage(PagingQueryBean queryBean, Page page);
}
