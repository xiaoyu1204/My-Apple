package com.example.myhttp.model.bean.shop;

import java.util.List;

public class OrderBean {


    /**
     * errno : 0
     * errmsg :
     * data : {"checkedAddress":{},"freightPrice":0,"checkedCoupon":{},"couponList":[{"id":1,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":1242142681,"order_id":4},{"id":2,"coupon_id":4,"coupon_number":"1000003379","user_id":1,"used_time":1242976699,"order_id":14},{"id":3,"coupon_id":4,"coupon_number":"1000018450","user_id":0,"used_time":0,"order_id":0},{"id":4,"coupon_id":4,"coupon_number":"1000023774","user_id":0,"used_time":0,"order_id":0},{"id":5,"coupon_id":4,"coupon_number":"1000039394","user_id":0,"used_time":0,"order_id":0},{"id":6,"coupon_id":4,"coupon_number":"1000049305","user_id":0,"used_time":0,"order_id":0},{"id":7,"coupon_id":4,"coupon_number":"1000052248","user_id":0,"used_time":0,"order_id":0},{"id":8,"coupon_id":4,"coupon_number":"1000061542","user_id":0,"used_time":0,"order_id":0},{"id":9,"coupon_id":4,"coupon_number":"1000070278","user_id":0,"used_time":0,"order_id":0},{"id":10,"coupon_id":4,"coupon_number":"1000080588","user_id":0,"used_time":0,"order_id":0},{"id":11,"coupon_id":4,"coupon_number":"1000091405","user_id":0,"used_time":0,"order_id":0},{"id":24,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":25,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":26,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":27,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":28,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":29,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":30,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":31,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0}],"couponPrice":0,"checkedGoodsList":[{"id":1430,"user_id":73404,"session_id":"1","goods_id":1051001,"goods_sn":"1051001","product_id":64,"goods_name":"Carat钻石煎锅28cm","market_price":159,"retail_price":159,"number":1,"goods_specifition_name_value":"","goods_specifition_ids":"","checked":1,"list_pic_url":"http://yanxuan.nosdn.127.net/f53ed57d9e23fda7e24dfd0e0a50c5d1.png"}],"goodsTotalPrice":159,"orderTotalPrice":159,"actualPrice":159}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * checkedAddress : {}
         * freightPrice : 0
         * checkedCoupon : {}
         * couponList : [{"id":1,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":1242142681,"order_id":4},{"id":2,"coupon_id":4,"coupon_number":"1000003379","user_id":1,"used_time":1242976699,"order_id":14},{"id":3,"coupon_id":4,"coupon_number":"1000018450","user_id":0,"used_time":0,"order_id":0},{"id":4,"coupon_id":4,"coupon_number":"1000023774","user_id":0,"used_time":0,"order_id":0},{"id":5,"coupon_id":4,"coupon_number":"1000039394","user_id":0,"used_time":0,"order_id":0},{"id":6,"coupon_id":4,"coupon_number":"1000049305","user_id":0,"used_time":0,"order_id":0},{"id":7,"coupon_id":4,"coupon_number":"1000052248","user_id":0,"used_time":0,"order_id":0},{"id":8,"coupon_id":4,"coupon_number":"1000061542","user_id":0,"used_time":0,"order_id":0},{"id":9,"coupon_id":4,"coupon_number":"1000070278","user_id":0,"used_time":0,"order_id":0},{"id":10,"coupon_id":4,"coupon_number":"1000080588","user_id":0,"used_time":0,"order_id":0},{"id":11,"coupon_id":4,"coupon_number":"1000091405","user_id":0,"used_time":0,"order_id":0},{"id":24,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":25,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":26,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":27,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":28,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":29,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":30,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0},{"id":31,"coupon_id":3,"coupon_number":"0","user_id":1,"used_time":0,"order_id":0}]
         * couponPrice : 0
         * checkedGoodsList : [{"id":1430,"user_id":73404,"session_id":"1","goods_id":1051001,"goods_sn":"1051001","product_id":64,"goods_name":"Carat钻石煎锅28cm","market_price":159,"retail_price":159,"number":1,"goods_specifition_name_value":"","goods_specifition_ids":"","checked":1,"list_pic_url":"http://yanxuan.nosdn.127.net/f53ed57d9e23fda7e24dfd0e0a50c5d1.png"}]
         * goodsTotalPrice : 159
         * orderTotalPrice : 159
         * actualPrice : 159
         */

        private CheckedAddressBean checkedAddress;
        private int freightPrice;
        private CheckedCouponBean checkedCoupon;
        private int couponPrice;
        private int goodsTotalPrice;
        private int orderTotalPrice;
        private int actualPrice;
        private List<CouponListBean> couponList;
        private List<CheckedGoodsListBean> checkedGoodsList;

        public CheckedAddressBean getCheckedAddress() {
            return checkedAddress;
        }

        public void setCheckedAddress(CheckedAddressBean checkedAddress) {
            this.checkedAddress = checkedAddress;
        }

        public int getFreightPrice() {
            return freightPrice;
        }

        public void setFreightPrice(int freightPrice) {
            this.freightPrice = freightPrice;
        }

        public CheckedCouponBean getCheckedCoupon() {
            return checkedCoupon;
        }

        public void setCheckedCoupon(CheckedCouponBean checkedCoupon) {
            this.checkedCoupon = checkedCoupon;
        }

        public int getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(int couponPrice) {
            this.couponPrice = couponPrice;
        }

        public int getGoodsTotalPrice() {
            return goodsTotalPrice;
        }

        public void setGoodsTotalPrice(int goodsTotalPrice) {
            this.goodsTotalPrice = goodsTotalPrice;
        }

        public int getOrderTotalPrice() {
            return orderTotalPrice;
        }

        public void setOrderTotalPrice(int orderTotalPrice) {
            this.orderTotalPrice = orderTotalPrice;
        }

        public int getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(int actualPrice) {
            this.actualPrice = actualPrice;
        }

        public List<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public List<CheckedGoodsListBean> getCheckedGoodsList() {
            return checkedGoodsList;
        }

        public void setCheckedGoodsList(List<CheckedGoodsListBean> checkedGoodsList) {
            this.checkedGoodsList = checkedGoodsList;
        }

        public static class CheckedAddressBean {
        }

        public static class CheckedCouponBean {
        }

        public static class CouponListBean {
            /**
             * id : 1
             * coupon_id : 3
             * coupon_number : 0
             * user_id : 1
             * used_time : 1242142681
             * order_id : 4
             */

            private int id;
            private int coupon_id;
            private String coupon_number;
            private int user_id;
            private int used_time;
            private int order_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getCoupon_number() {
                return coupon_number;
            }

            public void setCoupon_number(String coupon_number) {
                this.coupon_number = coupon_number;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getUsed_time() {
                return used_time;
            }

            public void setUsed_time(int used_time) {
                this.used_time = used_time;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }
        }

        public static class CheckedGoodsListBean {
            /**
             * id : 1430
             * user_id : 73404
             * session_id : 1
             * goods_id : 1051001
             * goods_sn : 1051001
             * product_id : 64
             * goods_name : Carat钻石煎锅28cm
             * market_price : 159
             * retail_price : 159
             * number : 1
             * goods_specifition_name_value :
             * goods_specifition_ids :
             * checked : 1
             * list_pic_url : http://yanxuan.nosdn.127.net/f53ed57d9e23fda7e24dfd0e0a50c5d1.png
             */

            private int id;
            private int user_id;
            private String session_id;
            private int goods_id;
            private String goods_sn;
            private int product_id;
            private String goods_name;
            private int market_price;
            private int retail_price;
            private int number;
            private String goods_specifition_name_value;
            private String goods_specifition_ids;
            private int checked;
            private String list_pic_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getSession_id() {
                return session_id;
            }

            public void setSession_id(String session_id) {
                this.session_id = session_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getMarket_price() {
                return market_price;
            }

            public void setMarket_price(int market_price) {
                this.market_price = market_price;
            }

            public int getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(int retail_price) {
                this.retail_price = retail_price;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getGoods_specifition_name_value() {
                return goods_specifition_name_value;
            }

            public void setGoods_specifition_name_value(String goods_specifition_name_value) {
                this.goods_specifition_name_value = goods_specifition_name_value;
            }

            public String getGoods_specifition_ids() {
                return goods_specifition_ids;
            }

            public void setGoods_specifition_ids(String goods_specifition_ids) {
                this.goods_specifition_ids = goods_specifition_ids;
            }

            public int getChecked() {
                return checked;
            }

            public void setChecked(int checked) {
                this.checked = checked;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }
        }
    }
}
