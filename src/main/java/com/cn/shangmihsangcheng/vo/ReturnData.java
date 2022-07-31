package com.cn.shangmihsangcheng.vo;

import com.cn.shangmihsangcheng.domain.Goods;
import com.cn.shangmihsangcheng.domain.Types;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReturnData {
    private Types type;
    private List<Goods>goodList;

}
