package com.cn.shangmihsangcheng.vo;

import com.cn.shangmihsangcheng.domain.Goods;
import com.cn.shangmihsangcheng.domain.Items;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    private List<Items> itemList;
    private Double total;
}
