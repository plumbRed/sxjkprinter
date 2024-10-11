package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.BomDTO;
import com.example.demo.dto.ResultVO;
import com.example.demo.entity.TBom;
import com.example.demo.mapper.BomMapper;
import com.example.demo.service.IBomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class BomServiceImpl extends ServiceImpl<BomMapper, TBom> implements IBomService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO saveBomData(List<BomDTO> bomDTOs) {
        if (null == bomDTOs || bomDTOs.isEmpty()) {
            log.info("BOM信息不存在");
            return new ResultVO(0, "BOM信息不存在",null);
        }
        //看传进来的工单号和物料编码是否都有，有的话更新，没有的话新增
        for (BomDTO bomDTO : bomDTOs) {
            TBom tBom = new TBom();
            BeanUtils.copyProperties(bomDTO, tBom);
            LambdaUpdateWrapper<TBom> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(TBom::getAufnr, tBom.getAufnr())
                    .eq(TBom::getMatnr, tBom.getMatnr());
            boolean updated = this.update(tBom, lambdaUpdateWrapper);
            if (!updated) {
                this.save(tBom);
            }
        }
        log.info("BOM信息保存成功");
        return new ResultVO(0, "BOM信息保存成功",null);
    }

    @Override
    public String getTrueMaterialCode(List<String> materialCodeList, String workOrder) {
        LambdaQueryWrapper<TBom> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(TBom::getMatnr, materialCodeList)
                .eq(TBom::getAufnr, workOrder);
        List<TBom> list = this.list(lambdaQueryWrapper);
        log.info("同时符合效率，等级，工单号的物料编码"+list);
        if (null == list || list.isEmpty()) {
            return "";
        }
        return list.get(0).getMatnr();
    }
}
