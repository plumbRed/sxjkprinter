package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.BomDTO;
import com.example.demo.dto.ResultVO;
import com.example.demo.dto.WorkOrderDetail;
import com.example.demo.dto.WorkOrderTotal;
import com.example.demo.entity.TBom;
import com.example.demo.entity.TWorkOrder;
import com.example.demo.mapper.BomMapper;
import com.example.demo.mapper.WorkOrderMapper;
import com.example.demo.service.IBomService;
import com.example.demo.service.IWorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BomServiceImpl extends ServiceImpl<BomMapper, TBom> implements IBomService {

    @Resource
    BomMapper bomMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO saveBomData(List<BomDTO> bomDTOs) {
        if(null == bomDTOs || bomDTOs.isEmpty()){
            log.info("BOM信息不存在");
            return new ResultVO(0,"BOM信息不存在");
        }
        //看传进来的工单号和物料编码是否都有，有的话更新，没有的话新增
        for(BomDTO bomDTO: bomDTOs){
            TBom tBom = new TBom();
            BeanUtils.copyProperties(bomDTO,tBom);
            LambdaUpdateWrapper<TBom> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(TBom::getAufnr, tBom.getAufnr())
                    .eq(TBom::getMatnr, tBom.getMatnr());
            boolean updated = this.update(tBom, lambdaUpdateWrapper);
            if(!updated){
                this.save(tBom);
            }
        }
        log.info("BOM信息保存成功");
        return new ResultVO(0,"BOM信息保存成功");
    }
}
