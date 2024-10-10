package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TWorkOrder {
    //工厂
    private String werks;

    //订单编号
    private String aufnr;

    //工单类型
    private String auart;

    //工单类型描述
    private String auarttxt;

    //开始日期
    private String gstrp;

    //结束日期
    private String gltrp;

    //定向标识
    private String zOrientationInd;

    //工单状态
    private String stat;

    //预留/相关需求的编号
    private Long rsnum;

    //研发订单号
    private String zzorder;

    //组件返工工单拼托标识
    private String zOnePallet;

    //组合号
    private String zCollectNo;

    //组合行号
    private Integer zCollectItm;

    //是否监造
    private String zzcotl;

    //抬头物料编号
    private String matnr;

    //基本计量单位
    private String meins;

    //总订单数量
    private Double gamng;

    //工作中心
    private String arbpl;

    //工作中心描述
    private String ktext;

    //类别
    private String zarbplLb;

    //生产版本
    private String verid;

    //BOM清单
    private String stlnr;

    //BOM版本
    private String stlal;

    //BOM用途
    private String stlan;

    //抬头物料描述
    private String maktx;

    //预算项目
    private String zzysxm;

    //预算项目描述
    private String zktext;

    //订单交货数量
    private Integer gwemg;

    //未知新字段
    private String combi;

    //未知新字段
    private String zzjhxh;


}
