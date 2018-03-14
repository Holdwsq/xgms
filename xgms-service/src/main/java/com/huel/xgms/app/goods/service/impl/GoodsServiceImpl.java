package com.huel.xgms.app.goods.service.impl;

import com.google.common.collect.Lists;
import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.dao.IGoodsInfoDao;
import com.huel.xgms.app.goods.service.IGoodsService;
import com.huel.xgms.base.bean.QnPutRet;
import com.huel.xgms.base.service.IQiNiuFileService;
import com.huel.xgms.base.util.XgmsFileUtils;
import com.huel.xgms.util.Contants;
import com.huel.xgms.util.UUIDMaker;
import com.qiniu.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        ArrayList<String> picNames = Lists.newArrayList();
        for (MultipartFile file : pictureList) {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileName = XgmsFileUtils.covertFileName(originalFilename);
            QnPutRet qnPutRet = qiNiuFileService.uploadInputStream(inputStream, fileName);
            /// todo 保存文件信息
            picNames.add(qnPutRet.getKey());
        }
        String mulPic = StringUtils.join(picNames, ",");

        goodsInfoBean.setPictureNames(mulPic);
        long time = System.currentTimeMillis();
        goodsInfoBean.setDeleteFlag(Contants.DELETE_FLAG_NO);
        goodsInfoBean.setId(UUIDMaker.generateUUID());
        goodsInfoBean.setUpdateTime(time);
        goodsInfoBean.setCreateTime(time);
        /// todo 保存商品信息
        goodsDao.save(goodsInfoBean);
    }
}
