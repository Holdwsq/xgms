package com.huel.xgms.app.goods.dao;

import com.huel.xgms.app.goods.bean.GoodsInfo;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;

/**
 * 商品信息
 * @author wsq
 * @date 2018/3/13
 */
@DB(
        table = "t_xgms_goods_info"
)
@Results({
        @Result(column = "c_id", property = "id"),
        @Result(column = "c_user_id", property = "userId"),
        @Result(column = "c_title", property = "title"),
        @Result(column = "c_description", property = "description"),
        @Result(column = "c_picture_names", property = "pictureNames"),
        @Result(column = "f_price", property = "price"),
        @Result(column = "c_type", property = "type"),
        @Result(column = "i_number", property = "number"),
        @Result(column = "l_create_time", property = "createTime"),
        @Result(column = "l_update_time", property = "updateTime"),
        @Result(column = "c_delete_flag", property = "deleteFlag")
})
public interface IGoodsInfoDao {

    String ALL_COLUMNS = "c_id, c_user_id, c_title, c_description, c_picture_names, f_price, "
            + " c_type, l_create_time, l_update_time, c_delete_flag";
    /**
     * 保存发布的商品信息
     * @param goodsInfoBean
     */
    @SQL("insert into #table (" + ALL_COLUMNS + ") "
            + " values "
            + " (:1.id, :1.userId, :1.title, :1.description, :1.pictureNames, :1.price, :1.type, "
            + " :1.createTime, :1.updateTime, :1.deleteFlag)")
    void save(GoodsInfo goodsInfoBean);
}
