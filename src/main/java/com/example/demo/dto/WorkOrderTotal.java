package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WorkOrderTotal {
    //物料特性值
    @JsonProperty("IT_DATA")
    private List<WorkOrderDetail> workOrderDetails;


}
