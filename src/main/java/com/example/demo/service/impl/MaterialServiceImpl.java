package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.Charts;
import com.example.demo.dto.MaterialData;
import com.example.demo.dto.ResultVO;
import com.example.demo.entity.TMaterialCharts;
import com.example.demo.mapper.MaterialChartsMapper;
import com.example.demo.service.IMaterialChartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class MaterialServiceImpl extends ServiceImpl<MaterialChartsMapper, TMaterialCharts> implements IMaterialChartsService {

    @Resource
    MaterialChartsMapper materialChartsMapper;

    @Override
    public ResultVO saveMaterialCharts(MaterialData materialData) {
        //0、还得排除它传空给我的情况
        if(null == materialData){
            return new ResultVO(1,"物料信息为空");
        }
        if(null == materialData.getCharts()){
            return new ResultVO(1,"物料特性为空");
        }

        //1、先看一下有没有这个物料编码，有的话就直接更新，没有就新增
        LambdaQueryWrapper<TMaterialCharts> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TMaterialCharts::getMatnr, materialData.getMatnr());
        Long num = materialChartsMapper.selectCount(lambdaQueryWrapper);
        //2-1、我们需要先将要保存的数据准备好
        List<TMaterialCharts> tMaterialCharts = convertToMaterial(materialData);
        //2-2、大于零走更新逻辑，否则走新增逻辑
        if(num > 0){
            for (TMaterialCharts materialCharts : tMaterialCharts) {
                LambdaUpdateWrapper<TMaterialCharts> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(TMaterialCharts::getMatnr, materialCharts.getMatnr())  // 根据物料编码更新
                        .eq(TMaterialCharts::getAtnam, materialCharts.getAtnam())
                        .set(TMaterialCharts::getMatkl, materialCharts.getMatkl())
                        .set(TMaterialCharts::getWgbez, materialCharts.getWgbez())
                        .set(TMaterialCharts::getAtbez, materialCharts.getAtbez())
                        .set(TMaterialCharts::getAtwrt, materialCharts.getAtwrt())
                        .set(TMaterialCharts::getAtwtb, materialCharts.getAtwtb());
                // 执行更新操作
                this.update(updateWrapper);
            }
        }else{
            this.saveBatch(tMaterialCharts);
        }
        return new ResultVO(0,"物料信息保存成功");
    }

    private List<TMaterialCharts> convertToMaterial(MaterialData materialData) {
        List<Charts> list = materialData.getCharts();
        List<TMaterialCharts> tMaterialChartsList = new ArrayList<>();
        for(Charts charts : list){
            TMaterialCharts tMaterialCharts = new TMaterialCharts();
            //物料编码
            tMaterialCharts.setMatnr(materialData.getMatnr());
            //物料组
            tMaterialCharts.setMatkl(materialData.getMatkl());
            //物料组描述
            tMaterialCharts.setWgbez(materialData.getWgbez());
            //特征名称
            tMaterialCharts.setAtnam(charts.getAtnam());
            //特征描述
            tMaterialCharts.setAtbez(charts.getAtbez());
            //特性值
            tMaterialCharts.setAtwrt(charts.getAtwrt());
            //特性值文本
            tMaterialCharts.setAtwtb(charts.getAtwtb());
            tMaterialChartsList.add(tMaterialCharts);
        }
        return tMaterialChartsList;
    }
}
