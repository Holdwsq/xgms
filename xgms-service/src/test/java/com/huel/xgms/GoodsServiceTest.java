package com.huel.xgms;

import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.bean.Home;
import com.huel.xgms.app.goods.service.IGoodsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * 商品服务接口
 * @author wsq
 * @date 2018/3/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class GoodsServiceTest {
    @Autowired
    private IGoodsService goodsService;

    @Test
    public void testHomeInfo(){
        Home homeInfo = goodsService.getHomeInfo();
        Assert.assertNotNull(homeInfo);
    }
    @Test
    public void testPublishGoods(){
        GoodsInfo goodsInfo = new GoodsInfo();
        try {
            goodsService.publishGoods(goodsInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
