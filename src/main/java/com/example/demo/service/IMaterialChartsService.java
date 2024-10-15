package com.example.demo.service;

import com.example.demo.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface IMaterialChartsService {
    /**
     * 保存物料信息
     * @param materialTotal 赛意的数据
     * @return 结果
     */
    ResultVO saveMaterialCharts(MaterialTotal materialTotal);

    /**
     * 通过工单号、效率、等级来查询数据
     */
    ResultVO<MaterialChartsDTO> getMaterialCode(GainMaterialCodeDTO gainMaterialCodeDTO);
}