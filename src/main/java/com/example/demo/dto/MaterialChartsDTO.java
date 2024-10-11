package com.example.demo.dto;

import lombok.Data;

@Data
public class MaterialChartsDTO {
    //物料编码
    private String materialCode;
    //定制化要求
    private String customRequire;
    //电性能（新）
    private String elePerformanceNew;
    //电性能
    private String elePerformance;
    //电池片边长
    private String eleLength;
    //电池片主栅线条数
    private String eleLine;
    //电池片转换率
    private String eleRate;
    //电池片质量等级
    private String eleLevel;
    //电池片PE工艺类型
    private String eleType;
    //电池片厂商
    private String eleManufacturer;
    //type1
    private String type1;
    //追溯信息
    private String traceInfo;
    //流水码说明
    private String traceCode;
    //单双封
    private String traceType;
    //产品类型
    private String productType;
    //定向/非定向
    private String productType2;
    //硅片产地
    private String elePlace;
    //硅料品牌
    private String eleBrand;
    //单双面&栅线
    private String eleSide;

}
