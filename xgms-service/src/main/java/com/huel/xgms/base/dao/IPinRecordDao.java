package com.huel.xgms.base.dao;

import com.huel.xgms.base.bean.PinRecordBean;
import com.huel.xgms.util.Constants;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;

/**
 * 手机验证码dao层
 * @author wsq
 * @date 2018/4/10
 */
@DB(
        table = "t_xgms_pin_record"
)
@Results({
        @Result(column = "i_id", property = "id"),
        @Result(column = "c_phone", property = "phone"),
        @Result(column = "c_code", property = "code"),
        @Result(column = "l_send_time", property = "sendTime"),
        @Result(column = "l_expiry_time", property = "expiryTime"),
        @Result(column = "c_delete_flag", property = "deleteFlag")
})
public interface IPinRecordDao {
    String All_COLUMNS = "i_id, c_phone, c_code, l_send_time, l_expiry_time, c_delete_flag";

    /**
     * 通过记录获取验证码发送
     * @param id
     * @return
     */
    @SQL("select * from #table t where t.c_id = id and c_delete_flag = " + Constants.DELETE_FLAG_NO)
    PinRecordBean get(int id);

    /**
     * 保存发送验证码记录
     * @param bean
     */
    @SQL("INSERT INTO #table (c_code, c_phone, l_send_time, l_expiry_time, c_delete_flag)"
            + " VALUES "
            + " (:code, :phone, :sendTime, :expiryTime, :deleteFlag)")
    void save(PinRecordBean bean);

    /**
     * 获取时间段内的对应手机号短信次数
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return count（record）
     */
    @SQL("select count(1) from #table t "
            + " where t.c_phone = :1 and c_delete_flag = " + Constants.DELETE_FLAG_NO
            + " #if(:2 != 0) and t.l_send_time >= :2 #end"
            + " #if(:3 != 0) and t.l_expiry_time <= :3 #end")
    int countByTime(String phone, long beginTime, long endTime);

    /**
     * 获取最近的验证码记录信息
     * @param phone 手机号
     * @return 验证码信息
     */
    @SQL("select " + All_COLUMNS + " from #table "
            + " where c_phone = :1 and l_send_time = "
            + " (select Max(l_send_time) from #table where c_phone = :1 and c_delete_flag = " + Constants.DELETE_FLAG_NO
            + " group by c_phone)")
    PinRecordBean getLastPin(String phone);

    /**
     * 删除过期的验证码
     * @param time 当前时间戳
     * @return 受影响的行数
     */
    @SQL("update #table set c_delete_flag = " + Constants.DELETE_FLAG_YES
            + " where l_expiry_time > :1")
    int delete(long time);
}
