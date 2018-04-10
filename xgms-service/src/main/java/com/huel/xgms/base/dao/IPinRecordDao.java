package com.huel.xgms.base.dao;

import com.huel.xgms.base.bean.PinRecordBean;
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
        @Result(column = "l_expiry_time", property = "expiryTime")
})
public interface IPinRecordDao {
    String All_COLUMNS = "i_id, c_phone, c_code, l_send_time, l_expiry_time";

    /**
     * 通过记录获取验证码发送
     * @param id
     * @return
     */
    @SQL("select * from #table t where t.c_id = id")
    PinRecordBean get(int id);

    /**
     * 保存发送验证码记录
     * @param bean
     */
    @SQL("INSERT INTO #table (c_code, c_phone, l_send_time, l_expiry_time)"
            + " VALUES "
            + " (:code, :phone, :sendTime, :expiryTime)")
    void save(PinRecordBean bean);

    /**
     * 获取时间段内的对应手机号短信次数
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return count（record）
     */
    @SQL("select count(1) from #table t "
            + " where t.c_phone = :1"
            + " #if(:2 != 0) and t.l_send_time >= :2 #end"
            + " #if(:3 != 0) and t.l_expiry_time <= :3 #end")
    int countByTime(String phone, long beginTime, long endTime);
}
