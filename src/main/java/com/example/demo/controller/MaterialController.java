package com.example.demo.controller;

import com.example.demo.dto.MaterialData;
import com.example.demo.dto.ResultVO;
import com.example.demo.service.IMaterialChartsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Tag(name = "Material", description = "物料数据")
public class MaterialController {

    @Resource
    private IMaterialChartsService IMaterialChartsService;

    @Operation(summary = "保存物料信息", description = "更新相同物料编码，新增不同的物料信息")
    @PostMapping("/api/material")
    public ResultVO receiveMaterialData(@RequestBody MaterialData materialData) {
        return IMaterialChartsService.saveMaterialCharts(materialData);
    }
}