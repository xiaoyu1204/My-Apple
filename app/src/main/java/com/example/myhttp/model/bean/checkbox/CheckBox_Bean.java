package com.example.myhttp.model.bean.checkbox;

import java.util.List;

public class CheckBox_Bean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", select=" + select +
                    '}';
        }

        /**
         * name : item1
         * select : true
         */

        private String name;
        private boolean select;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }
}
