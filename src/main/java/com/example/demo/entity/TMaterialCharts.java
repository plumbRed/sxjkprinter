package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_material_charts")
public class TMaterialCharts {

    //物料编码
    private String matnr;

    //物料组
    private String matkl;

    //物料组描述
    private String wgbez;

    //特征名称
    private String atnam;

    //特性描述
    private String atbez;

    //特性值
    private String atwrt;

    //特性值文本
    private String atwtb;

}