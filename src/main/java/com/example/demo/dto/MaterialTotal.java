package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MaterialTotal {

    @JsonProperty("ET_DATA")
    private List<MaterialData> materialDataList;

}
