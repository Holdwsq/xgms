package com.huel.xgms.app.goods.dao;

import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.bean.GoodsInfoBean;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.util.Contants;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.plugin.page.Page;

import java.util.List;

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

    /**
     * 查询商品列表
     * @param queryBean
     * @param userId 查询对应人员的发布商品列表
     * @return
     */
    @SQL("select " + ALL_COLUMNS + " from #table t where 1 = 1"
            + " and t.c_delete_flag = " + Contants.DELETE_FLAG_NO
            + " #if(:1.id != null) and t.c_id = :1.id #end "
            + " #if(:1.key != null) and t.c_title like '%'||:1.key||'%' #end"
            + " #if(:2 != null) and t.c_user_id = :2 #end"
            + " order by t.l_create_time desc")
    List<GoodsInfo> list(PagingQueryBean queryBean, String userId, Page page);

    /**
     * 获取对应id商品详情
     * @param goodsId 商品id
     * @return 商品详情
     */
    @SQL("select * from #table t "
            + " where t.c_id = :1"
            + " and t.c_delete_flag = " + Contants.DELETE_FLAG_NO)
    GoodsInfo getGoodsDetail(String goodsId);
}
