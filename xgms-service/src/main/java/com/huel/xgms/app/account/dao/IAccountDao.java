package com.huel.xgms.app.account.dao;

import com.huel.xgms.app.user.bean.User;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;

/**
 * 数据持久层操作接口
 * @author wsq
 * @date 2018/1/7
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
public interface IAccountDao {
    String COLUMNS = "c_name, c_hobby, c_preview_id, c_file_id, c_sex, d_birthday, c_brief, c_school, c_delete_flag, "
            + "n_create_time, n_update_time, c_phone, c_auth, c_account_name, c_pwd, n_age";
    String ALL_COLUMNS = "c_id, " + COLUMNS;

    /**
     * 用户登录数据库操作
     * @param accountName 账户名
     * @param pwd 密码
     * @return 返回对应的实体类
     */
    @SQL("select " + ALL_COLUMNS + " from #table t where t.c_account_name = :1 and t.c_pwd = :2")
    User login(String accountName, String pwd);

    /**
     * 检测账户名是否已被注册
     * @return User
     * @param accountName 账户名
     * @param userId 用户ID 可为空
     */
    @SQL("select " + ALL_COLUMNS + " from #table t where t.c_account_name = :1"
            + "#if(:2 != null && :2 != '') and t.id != :2 #end")
    User checkAccountName(String accountName, String userId);

    /**
     * 用户账户注册
     * @param user 账户信息
     */
    @SQL("INSERT INTO #table "
            + " (c_id, c_account_name, c_pwd, c_delete_flag, n_create_time, n_update_time, c_auth)"
            + " VALUES (:1.id, :1.accountName, :1.pwd, :1.deleteFlag, :1.createTime, :1.updateTime, :1.auth)")
    void register(User user);

    /**
     * 修改密码
     * @param userId
     * @param pwd
     */
    @SQL("UPDATE #table t SET t.c_pwd = :2 where t.c_id = :1")
    void modifyPwd(String userId, String pwd);

    /**
     * 通过手机号获取用户信息
     * @param phone 手机号
     * @return 用户详情信息
     */
    @SQL("select " + ALL_COLUMNS + " from #table t where t.c_phone = :1")
    User getUserByPhone(String phone);

    /**
     * 增加手机注册的用户
     * @param user 用户信息
     */
    @SQL("insert into #table (c_id, c_account_name, c_phone, c_delete_flag, n_create_time, n_update_time, c_auth)")
    void addPhoneUser(User user);
}
