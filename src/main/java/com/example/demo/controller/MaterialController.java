package com.example.demo.controller;

import com.example.demo.dto.*;
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
    private IMaterialChartsService iMaterialChartsService;

    @Operation(summary = "保存物料信息", description = "更新相同物料编码，新增不同的物料信息")
    @PostMapping("/material/saveMaterialData")
    public ResultVO saveMaterialData(@RequestBody MaterialTotal materialTotal) {
        return iMaterialChartsService.saveMaterialCharts(materialTotal);
    }

    /**
     * 通过效率，等级，工单号获取物料编码信息
     */
    @Operation(summary = "获取物料信息", description = "通过等级、效率、工单号获取物料编码")
    @PostMapping("/material/getMaterialInfo")
    public ResultVO<MaterialChartsDTO> getMaterialInfo(@RequestBody GainMaterialCodeDTO gainMaterialCode) {
        return iMaterialChartsService.getMaterialCode(gainMaterialCode);
    }


}