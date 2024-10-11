package com.example.demo.service;

import com.example.demo.dto.GainMaterialCodeDTO;
import com.example.demo.dto.MaterialChartsDTO;
import com.example.demo.dto.MaterialData;
import com.example.demo.dto.ResultVO;
import org.springframework.stereotype.Service;

@Service
public interface IMaterialChartsService {
    /**
     * 保存物料信息
     * @param materialData 赛意的数据
     * @return 结果
     */
    ResultVO saveMaterialCharts(MaterialData materialData);

    /**
     * 通过工单号、效率、等级来查询数据
     */
    MaterialChartsDTO getMaterialCode(GainMaterialCodeDTO gainMaterialCodeDTO);
}