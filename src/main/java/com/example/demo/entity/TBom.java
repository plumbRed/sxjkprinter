package com.example.demo.entity;

import lombok.Data;

@Data
public class TBom {
    //订单编号原内容中没有的，但数据库里面需要做绑定
    private String aufnr;

    //预留 / 相关需求的项目编号
    private int rspos;

    //行状态 空/D（删除）
    private String linestat;

    //工作中心
    private String arbpl;

    //抬头物料编码  这个做第二个id做更新吧
    private String matnr;

    //需求数量
    private Double bdmng;

    //替代项目
    private String alpos;

    //反冲
    private String rgekz;

    //虚拟项目标识
    private String dumps;

    //指示符：散装物料
    private String schgt;

    //指示符：联合产品
    private String kzkup;

    //副产品标识
    private String zfcp;

    //基本计量单位
    private String meins;

    //硅片批次信息
    private String zcharg;

    //BOM 项目文本（行1）
    private String potx1;

    //排序字符串
    private String sortf;

    //单个用量
    private Double bomqty;

    //物料描述
    private String maktx;

    //替代项目：组
    private String alpgr;

    //替代项目：策略
    private String alpst;

    //使用百分比
    private Integer ewahr;

    //更新标识
    private String vbkz;

    //未知新内容？
    private String baugr;

}
