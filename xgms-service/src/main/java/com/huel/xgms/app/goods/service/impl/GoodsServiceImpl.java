package com.huel.xgms.app.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.huel.xgms.app.goods.bean.*;
import com.huel.xgms.app.goods.dao.IGoodsInfoDao;
import com.huel.xgms.app.goods.service.IGoodsService;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.base.bean.QnPutRet;
import com.huel.xgms.base.service.IQiNiuFileService;
import com.huel.xgms.base.util.XgmsFileUtils;
import com.huel.xgms.system.bean.SystemConfigCode;
import com.huel.xgms.system.service.ISystemConfigService;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.UUIDMaker;
import org.jfaster.mango.plugin.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
    @Autowired
    private IUserService userService;

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
        String mulPic = StringUtils.collectionToDelimitedString(picNames, ",");

        goodsInfoBean.setPictureNames(mulPic);
        long time = System.currentTimeMillis();
        goodsInfoBean.setDeleteFlag(Constants.DELETE_FLAG_NO);
        goodsInfoBean.setId(UUIDMaker.generateUUID());
        goodsInfoBean.setUpdateTime(time);
        goodsInfoBean.setCreateTime(time);
        /// todo 保存商品信息
        goodsDao.save(goodsInfoBean);
    }

    @Override
    public PageData<GoodsInfoBean> list(PagingQueryBean queryBean) {
        PageData<GoodsInfoBean> pageData = new PageData<>();
        // 生成 mongo 的 page对象
        Page page = new Page(queryBean.getPageNo(), queryBean.getPageSize());

        List<GoodsInfo> goodsInfoList = goodsDao.list(queryBean, null, page);
        pageData.setPageNo(page.getPageNum());
        pageData.setPageSize(page.getPageSize());

        if (CollectionUtils.isEmpty(goodsInfoList)){
            pageData.setData(null);
            return pageData;
        }
        // 获取批量的用户信息
        List<GoodsInfoBean> goodsInfoBeanList = Lists.newArrayList();
        Set<String> userIds = Sets.newHashSet();

        for (GoodsInfo goodsInfo : goodsInfoList) {
            GoodsInfoBean bean = new GoodsInfoBean();
            BeanUtils.copyProperties(goodsInfo, bean);
            userIds.add(goodsInfo.getUserId());
            String pictureNames = goodsInfo.getPictureNames();
            String[] split = pictureNames.split(",");
            bean.setFileUrls(new ArrayList<>(Arrays.asList(split)));
            goodsInfoBeanList.add(bean);
        }
        // 没有用户证明数据有问题，直接返回null
        List<User> userList = userService.list(userIds);
        if(CollectionUtils.isEmpty(userList)){
            pageData.setData(null);
            return pageData;
        }
        Map<String, User> map = Maps.newHashMap();
        for (User user : userList) {
            if (map.get(user.getId()) != null){
                continue;
            }
            map.put(user.getId(), user);
        }
        for (GoodsInfoBean goodsInfoBean : goodsInfoBeanList) {
            User user = map.get(goodsInfoBean.getUserId());
            goodsInfoBean.setUserName(user != null ? user.getAccountName() : "无名氏");
            goodsInfoBean.setUserPortrait(user !=null ? user.getFileId() : null);
        }
        pageData.setData(goodsInfoBeanList);
        pageData.setRecordCount(page.getTotal());
        LOG.info("分页获取到的商品信息为：" + JSON.toJSONString(goodsInfoBeanList));
        return pageData;
    }

    @Override
    public Home getHomeInfo() {
        Home home = new Home();
        /// todo 获取轮播图
        List<BaseBean> list = Lists.newLinkedList();
        home.setHomeBanners(list);
        // todo 获取折扣信息
        List<BaseBean> discounts = Lists.newLinkedList();
        home.setHomeDiscounts(discounts);
        PagingQueryBean queryBean = new PagingQueryBean();
        PageData<GoodsInfoBean> pageData = list(queryBean);
        home.setGoodsInfos(pageData);

        LOG.info("获取主页信息为：{}", JSON.toJSONString(home));
        return home;
    }

    @Override
    public GoodsInfoBean getDetail(String goodsId) {

        if (StringUtils.isEmpty(goodsId)){
            return null;
        }
        // 获取商品详情
        GoodsInfo goodsInfo = goodsDao.getGoodsDetail(goodsId);
        if (goodsInfo == null){
            throw new RuntimeException("商品不存在或被删除");
        }
        GoodsInfoBean bean = new GoodsInfoBean();
        BeanUtils.copyProperties(goodsInfo, bean);
        String pictureNames = goodsInfo.getPictureNames();
        String[] split = pictureNames.split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        bean.setFileUrls(list);
        // 获取用户信息
        String userId = bean.getUserId();
        User userInfo = userService.getUserInfo(userId);
        bean.setUserPortrait(userInfo.getFileId());
        bean.setUserName(userInfo.getAccountName());
        /// todo 添加该商品的评论 考虑是否放在同一个请求中
        List<GoodsCommentBean> commentList = Lists.newArrayList();
        bean.setCommentList(commentList);
        return bean;
    }
}
