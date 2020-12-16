package com.example.myhttp.model.bean.checkbox;

import java.util.List;

public class CheckBox_Bean1 {
    private String name;
    private boolean select;

    @Override
    public String toString() {
        return "CheckBox_Bean1{" +
                "name='" + name + '\'' +
                ", select=" + select +
                '}';
    }

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
