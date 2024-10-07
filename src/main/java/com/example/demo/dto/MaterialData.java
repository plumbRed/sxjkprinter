package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MaterialData {

    //物料编码
    @JsonProperty("MATNR")
    private String matnr;

    //物料组
    @JsonProperty("MATKL")
    private String matkl;

    //物料组描述
    @JsonProperty("WGBEZ")
    private String wgbez;

    //物料特性值
    @JsonProperty("CHARTS")
    private List<Charts> charts;
}