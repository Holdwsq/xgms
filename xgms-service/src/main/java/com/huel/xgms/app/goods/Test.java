package com.huel.xgms.app.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2018/3/13
 */
public class Test {
    public static void main(String... args){
        List<Bean> beanList = initData();
        // 获取顶级bean
        List<Bean> topBeanList = Lists.newArrayList();
        for (Bean bean : beanList) {
            if (bean.getpId() == null){
                topBeanList.add(bean);
            }
        }
        Map<String, List<Bean>> map = Maps.newHashMap();
        // 遍历顶级bean
        for (Bean bean : topBeanList) {
            List<Bean> tarList = findSubBeans(beanList, bean.getId());
            map.put(bean.getId(), tarList);
        }
    }

    /**
     * 返回下级Bean
     * @param srcList
     * @param tarList
     * @param topId
     */
    private static List<Bean> findSubBeans(List<Bean> srcList, String topId) {
        if (CollectionUtils.isEmpty(srcList)){
            return null;
        }
        List<Bean> tarList =  Lists.newArrayList();

        for (Bean bean : srcList) {
            if (bean.getpId() ==null){
                continue;
            }
            if (bean.getpId().equals(topId)){
                tarList.add(bean);
                tarList.addAll(findSubBeans(srcList, bean.getId()));
            }
        }
        return tarList;
    }

    private static List<Bean> initData() {
        List<Bean> list = Lists.newLinkedList();
        Bean a = new Bean("0", "A", null);
        list.add(a);
        Bean b = new Bean("1", "B", "0");
        list.add(b);
        Bean c = new Bean("2", "C", "1");
        list.add(c);
        Bean bean = new Bean("a", "FF", null);
        list.add(bean);
        Bean bean1 = new Bean("aa", "FFF", "a");
        list.add(bean1);
        return list;
    }

    static class Bean{
        private String id;
        private String name;
        private String pId;

        Bean(String id, String name, String pId){
            this.id = id;
            this.name = name;
            this.pId = pId;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
        }
    }
}
