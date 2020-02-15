//package com.coins.common.enums;
//
//
//import com.juqi.cate.domain.message.weixin.QuickRecommendParams;
//import com.juqi.cate.domain.message.weixin.VipArriveParams;
//import com.juqi.cate.domain.message.weixin.VipCheckoutParams;
//import com.juqi.cate.weixinsdk.enums.WeixinTemplateKeyBaseEnum;
//
//public interface WeixinTemplateKeyEnum extends WeixinTemplateKeyBaseEnum {
//
//    enum Customer implements WeixinTemplateKeyEnum {
//        VIP_ARRIVE("customer.vip.arrive", "就餐通知", VipArriveParams.class),
//        VIP_CHECKOUT("customer.vip.checkout", "结账通知", VipCheckoutParams.class);
//
//        private String templateKey;
//        private String desc;
//        private Class<?> classType;
//        private Customer(String _templateKey, String _desc, Class<?> _classType) {
//            this.templateKey = _templateKey;
//            this.desc = _desc;
//            this.classType = _classType;
//        }
//
//        @Override
//        public String getDescription() {
//            return desc;
//        }
//
//        @Override
//        public String getTemplateKey() {
//            return templateKey;
//        }
//
//        @Override
//        public Class<?> getClassType() {
//            return classType;
//        }
//    }
//
//    enum Dish implements WeixinTemplateKeyEnum {
//        QUICK_RECOMMEND("dish.quick.recommend", "菜品急推提醒", QuickRecommendParams.class);
//
//        private String templateKey;
//        private String desc;
//        private Class<?> classType;
//        private Dish(String _templateKey, String _desc, Class<?> _classType) {
//            this.templateKey = _templateKey;
//            this.desc = _desc;
//            this.classType = _classType;
//        }
//
//        @Override
//        public String getDescription() {
//            return desc;
//        }
//
//        @Override
//        public String getTemplateKey() {
//            return templateKey;
//        }
//
//        @Override
//        public Class<?> getClassType() {
//            return classType;
//        }
//    }
//}
