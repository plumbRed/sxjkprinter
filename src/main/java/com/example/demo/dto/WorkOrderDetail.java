package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WorkOrderDetail {

    //工厂
    @JsonProperty("WERKS")
    private String werks;

    //订单编号
    @JsonProperty("AUFNR")
    private String aufnr;

    //工单类型
    @JsonProperty("AUART")
    private String auart;

    //工单类型描述
    @JsonProperty("AUARTTXT")
    private String auarttxt;

    //开始日期
    @JsonProperty("GSTRP")
    private String gstrp;

    //结束日期
    @JsonProperty("GLTRP")
    private String gltrp;

    //定向标识
    @JsonProperty("Z_ORIENTATION_IND")
    private String zOrientationInd;

    //工单状态
    @JsonProperty("STAT")
    private String stat;

    //预留/相关需求的编号
    @JsonProperty("RSNUM")
    private Long rsnum;

    //研发订单号
    @JsonProperty("ZZORDER")
    private String zzorder;

    //组件返工工单拼托标识
    @JsonProperty("Z_ONE_PALLET")
    private String zOnePallet;

    //组合号
    @JsonProperty("Z_COLLECT_NO")
    private String zCollectNo;

    //组合行号
    @JsonProperty("Z_COLLECT_ITM")
    private int zCollectItm;

    //是否监造
    @JsonProperty("ZZCOTL")
    private String zzcotl;

    //抬头物料编号
    @JsonProperty("MATNR")
    private String matnr;

    //基本计量单位
    @JsonProperty("MEINS")
    private String meins;

    //总订单数量
    @JsonProperty("GAMNG")
    private double gamng;

    //工作中心
    @JsonProperty("ARBPL")
    private String arbpl;

    //工作中心描述
    @JsonProperty("KTEXT")
    private String ktext;

    //类别
    @JsonProperty("ZARBPL_LB")
    private String zarbplLb;

    //生产版本
    @JsonProperty("VERID")
    private String verid;

    //BOM清单
    @JsonProperty("STLNR")
    private String stlnr;

    //BOM版本
    @JsonProperty("STLAL")
    private String stlal;

    //BOM用途
    @JsonProperty("STLAN")
    private String stlan;

    //抬头物料描述
    @JsonProperty("MAKTX")
    private String maktx;

    //预算项目
    @JsonProperty("ZZYSXM")
    private String zzysxm;

    //预算项目描述
    @JsonProperty("ZKTEXT")
    private String zktext;

    //订单交货数量
    @JsonProperty("GWEMG")
    private int gwemg;

    //未知新字段
    @JsonProperty("COMBI")
    private String combi;

    //未知新字段
    @JsonProperty("ZZJHXH")
    private String zzjhxh;

    //BOM明细表
    @JsonProperty("BOMLIST")
    private List<BomDTO> bomDTO;
}
