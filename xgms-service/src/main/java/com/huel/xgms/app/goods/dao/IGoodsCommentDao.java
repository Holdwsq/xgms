package com.huel.xgms.app.goods.dao;

import com.huel.xgms.app.goods.bean.GoodsComment;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.util.Contants;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.plugin.page.Page;

import java.util.List;

/**
 * @author admin
 * @description
 * @date 2018/3/22
 */
@DB(
        table = "t_xgms_goods_comment"
)
@Results({
        @Result(column = "c_id", property = "id"),
        @Result(column = "c_goods_id", property = "goodsId"),
        @Result(column = "c_commenter_id", property = "commenterId"),
        @Result(column = "c_content", property = "content"),
        @Result(column = "c_reply_comment_id", property = "replyCommentId"),
        @Result(column = "l_create_time", property = "createTime"),
        @Result(column = "l_update_time", property = "updateTime"),
        @Result(column = "c_delete_flag", property = "deleteFlag")
})
public interface IGoodsCommentDao {
    String ALL_COLUMNS = "c_id, c_goods_id, c_commenter_id, c_content, c_reply_comment_id, "
            + " l_create_time, l_update_time, c_delete_flag";

    /**
     * 分页获取顶级的商品评价 => replyCommentId is null
     * @param queryBean 查询bean
     * @param page 分页bean
     * @return List
     */
    @SQL("SELECT * FROM #table t where t.c_delete_flag = " + Contants.DELETE_FLAG_NO
            + " and t.c_goods_id = :1.id and t.c_reply_comment_id is null"
            + " #if(:1.key != null) and t.c_content like '%'||:1.key||'%' #end "
            + " #if(:1.beginTime != null) and t.l_update_time >= :1.beginTime #end "
            + " #if(:1.endTime != null) and t.l_update_time <= :1.endTime #end "
            + " ORDER BY t.l_update_time DESC")
    List<GoodsComment> list(PagingQueryBean queryBean, Page page);
}
