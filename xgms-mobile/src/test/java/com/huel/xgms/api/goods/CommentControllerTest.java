package com.huel.xgms.api.goods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.huel.xgms.app.goods.bean.GoodsCommentBean;
import com.huel.xgms.base.BaseTest;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.httpbean.ResponseBean;
import com.huel.xgms.util.HttpRequestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 评价控制层测试
 * @author wsq
 * @date 2018/3/23
 */
public class CommentControllerTest extends BaseTest{
    /**
     * 单元测试发表评论
     */
    @Test
    public void testPubLishComment(){
        String publishUri = "/pri/comments";
        String publishUrl = baseUrl + publishUri;

        Map<String, String> map = Maps.newHashMap();
        map.put("content", "你说的是真的么，求图哈");
        map.put("goodsId", "5f05a975ea7d4302933f05bbd67d6c7d");
        map.put("replyCommentId", "765cd1a93f3d4e15b317815477afe7dc");
        String resp = HttpRequestUtils.post(publishUrl, map);
        ResponseBean responseBean = JSON.parseObject(resp, ResponseBean.class);

        if (ResponseBean.STATUS_SUCCESS.equals(responseBean.getStatus())){
            System.out.println("发布成功, 评论id:" + responseBean.getData());
        }else{
            System.out.println("发布失败：" + responseBean.getMessage());
        }
    }

    /**
     * 单元测试-获取评论列表
     */
    @Test
    public void testGetComments(){
        String commentsUri = "/pub/comments";
        String commentsUrl = baseUrl + commentsUri;
        String param = "id=5f05a975ea7d4302933f05bbd67d6c7d";
        String respData = HttpRequestUtils.get(commentsUrl, param);

        ResponseBean<PageData<GoodsCommentBean>> pageDataResponseBean = JSON.parseObject(respData, new TypeReference<ResponseBean<PageData<GoodsCommentBean>>>() {});
        if (ResponseBean.STATUS_SUCCESS.equals(pageDataResponseBean.getStatus())){
            PageData<GoodsCommentBean> pageData = pageDataResponseBean.getData();
            List<GoodsCommentBean> data = pageData.getData();
            Assert.assertNotNull(data);
            for (GoodsCommentBean bean : data) {
                System.out.println(JSON.toJSONString(bean));
            }
        }else{
            System.out.println("获取评论列表失败：" + pageDataResponseBean.getMessage());
        }
    }
}
