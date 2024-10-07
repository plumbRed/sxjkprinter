package com.example.demo.service;

import com.example.demo.dto.MaterialData;
import com.example.demo.dto.ResultVO;
import org.springframework.stereotype.Service;

@Service
public interface IMaterialChartsService {
    // 可以添加自定义方法
    ResultVO saveMaterialCharts(MaterialData materialData);
}