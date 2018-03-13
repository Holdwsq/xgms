package com.huel.xgms.app.goods.service.impl;

import com.google.common.collect.Lists;
import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.dao.IGoodsInfoDao;
import com.huel.xgms.app.goods.service.IGoodsService;
import com.huel.xgms.base.service.IQiNiuFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务接口实现
 * @author wsq
 * @date 2018/3/13
 */
@Service
public class GoodsServiceImpl implements IGoodsService{

    private static final Logger LOG = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private IQiNiuFileService qiNiuFileService;
    @Autowired
    private IGoodsInfoDao goodsDao;
    @Override
    public void publishGoods(GoodsInfo goodsInfoBean) throws IOException {
        // 上传图片
        List<MultipartFile> pictureList = goodsInfoBean.getFiles();
        // 图片上传后的文件路径
        ArrayList<String> picUrls = Lists.newArrayList();
        for (MultipartFile file : pictureList) {
            InputStream inputStream = file.getInputStream();

        }
    }
}
