package com.huel.xgms.app.dao;

import com.huel.xgms.app.bean.User;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

/**
 * @description 数据持久层操作接口
 * @author wsq
 * @date 2018/1/7
 */
@DB(
        table = "t_user"
)
public interface IAccountDao {
    String COLUMNS = "c_id,";
    String ALL_COLUMNS = "c_pwd, " + COLUMNS;

    /**
     * @description 用户登录数据库操作
     * @param account
     * @param pwd
     * @return 返回对应的实体类
     */
    @SQL("select " + COLUMNS + " from #table t where t.c_account = :1 and t.c_pwd = :2")
    User login(String account, String pwd);
}
