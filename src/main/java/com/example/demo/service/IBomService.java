package com.example.demo.service;

import com.example.demo.dto.BomDTO;
import com.example.demo.dto.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBomService {

    /**
     * 新增或者修改bom信息
     * @param bomDTOs bom集合
     * @return 结果
     */
    ResultVO saveBomData(List<BomDTO> bomDTOs);


}