package com.huel.xgms.app.goods.service.impl;

import com.google.common.collect.Lists;
import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.bean.GoodsInfoBean;
import com.huel.xgms.app.goods.dao.IGoodsInfoDao;
import com.huel.xgms.app.goods.service.IGoodsService;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.base.bean.QnPutRet;
import com.huel.xgms.base.service.IQiNiuFileService;
import com.huel.xgms.base.util.XgmsFileUtils;
import com.huel.xgms.system.bean.SystemConfigCode;
import com.huel.xgms.system.service.ISystemConfigService;
import com.huel.xgms.util.Contants;
import com.huel.xgms.util.UUIDMaker;
import com.qiniu.util.StringUtils;
import org.jfaster.mango.plugin.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
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
    @Autowired
    private ISystemConfigService systemConfigService;

    @Override
    public void publishGoods(GoodsInfo goodsInfoBean) throws IOException {
        // 上传图片
        List<MultipartFile> pictureList = goodsInfoBean.getFiles();
        // 图片上传后的文件路径
        ArrayList<String> picNames = Lists.newArrayList();
        // 获取文件 BUCK访问路径
        String bucketUrl = systemConfigService.getValueByCode(SystemConfigCode.QINIU_BUCKET_XGMS1_URL);
        for (MultipartFile file : pictureList) {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileName = XgmsFileUtils.covertFileName(originalFilename);
            QnPutRet qnPutRet = qiNiuFileService.uploadInputStream(inputStream, fileName);
            /// todo 保存文件信息
            picNames.add(bucketUrl + qnPutRet.getKey());
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

    @Override
    public PageData list(PagingQueryBean queryBean) {
        PageData pageData = new PageData();
        // 生成 mongo 的 page对象
        Page page = new Page(queryBean.getPageNo(), queryBean.getPageSize());

        List<GoodsInfo> goodsInfoList = goodsDao.list(queryBean, null, page);
        List<GoodsInfoBean> goodsInfoBeanList = Lists.newArrayList();
        for (GoodsInfo goodsInfo : goodsInfoList) {
            GoodsInfoBean bean = new GoodsInfoBean();
            bean.setCreateTime(goodsInfo.getCreateTime());
            bean.setId(goodsInfo.getId());
            bean.setPictureNames(bean.getPictureNames());
            bean.setPrice(goodsInfo.getPrice());
            bean.setDescription(goodsInfo.getDescription());
        }
        return null;
    }
}
