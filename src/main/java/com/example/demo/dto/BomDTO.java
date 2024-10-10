package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BomDTO {

    //订单编号原内容中没有的，但数据库里面需要做绑定
    @JsonProperty("AUFNR")
    private String aufnr;

    //预留 / 相关需求的项目编号
    @JsonProperty("RSPOS")
    private int rspos;

    //行状态 空/D（删除）
    @JsonProperty("LINESTAT")
    private String linestat;

    //工作中心
    @JsonProperty("ARBPL")
    private String arbpl;

    //抬头物料编码  这个做第二个id做更新吧
    @JsonProperty("MATNR")
    private String matnr;

    //需求数量
    @JsonProperty("BDMNG")
    private Double bdmng;

    //替代项目
    @JsonProperty("ALPOS")
    private String alpos;

    //反冲
    @JsonProperty("RGEKZ")
    private String rgekz;

    //虚拟项目标识
    @JsonProperty("DUMPS")
    private String dumps;

    //指示符：散装物料
    @JsonProperty("SCHGT")
    private String schgt;

    //指示符：联合产品
    @JsonProperty("KZKUP")
    private String kzkup;

    //副产品标识
    @JsonProperty("ZFCP")
    private String zfcp;

    //基本计量单位
    @JsonProperty("MEINS")
    private String meins;

    //硅片批次信息
    @JsonProperty("ZCHARG")
    private String zcharg;

    //BOM 项目文本（行1）
    @JsonProperty("POTX1")
    private String potx1;

    //排序字符串
    @JsonProperty("SORTF")
    private String sortf;

    //单个用量
    @JsonProperty("BOMQTY")
    private Double bomqty;

    //物料描述
    @JsonProperty("MAKTX")
    private String maktx;

    //替代项目：组
    @JsonProperty("ALPGR")
    private String alpgr;

    //替代项目：策略
    @JsonProperty("ALPST")
    private String alpst;

    //使用百分比
    @JsonProperty("EWAHR")
    private Integer ewahr;

    //更新标识
    @JsonProperty("VBKZ")
    private String vbkz;

    //未知新内容？
    @JsonProperty("BAUGR")
    private String baugr;
}
