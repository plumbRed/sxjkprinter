package com.example.demo.controller;

import com.example.demo.dto.MaterialData;
import com.example.demo.dto.ResultVO;
import com.example.demo.entity.TMaterialCharts;
import com.example.demo.service.IMaterialChartsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Tag(name = "Material", description = "保存物料数据")
public class MaterialController {

    @Resource
    private IMaterialChartsService IMaterialChartsService;

    @PostMapping("/api/material")
    public ResultVO receiveMaterialData(@RequestBody MaterialData materialData) {
        return IMaterialChartsService.saveMaterialCharts(materialData);
    }
    private List<TMaterialCharts> convertToMaterial(MaterialData materialData) {

        return null;
    }
}