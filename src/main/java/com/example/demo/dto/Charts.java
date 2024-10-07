package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Charts {

    //特征名称
    @JsonProperty("ATNAM")
    private String atnam;

    //特性描述
    @JsonProperty("ATBEZ")
    private String atbez;

    //特性值
    @JsonProperty("ATWRT")
    private String atwrt;

    //特性值文本
    @JsonProperty("ATWTB")
    private String atwtb;
}
