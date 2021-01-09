package com.xzs.vhr.model;

import java.util.List;

//用来保存分页查询的结果
//主要给vue返回当前要展示的数据，和一共查了多少记录
public class RespPageBean {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
