package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.*;
import com.example.demo.entity.TMaterialCharts;
import com.example.demo.mapper.MaterialChartsMapper;
import com.example.demo.service.IBomService;
import com.example.demo.service.IMaterialChartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MaterialServiceImpl extends ServiceImpl<MaterialChartsMapper, TMaterialCharts> implements IMaterialChartsService {

    @Resource
    MaterialChartsMapper materialChartsMapper;

    @Resource
    IBomService iBomService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO saveMaterialCharts(MaterialData materialData) {
        log.info("物料数据: " + materialData);
        //0、还得排除它传空给我的情况
        if (null == materialData) {
            return new ResultVO(1, "物料信息为空", null);
        }
        if (null == materialData.getCharts()) {
            return new ResultVO(1, "物料特性为空", null);
        }

        //1、先看一下有没有这个物料编码，有的话就直接更新，没有就新增
        LambdaQueryWrapper<TMaterialCharts> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TMaterialCharts::getMatnr, materialData.getMatnr());
        Long num = materialChartsMapper.selectCount(lambdaQueryWrapper);
        //2-1、我们需要先将要保存的数据准备好
        List<TMaterialCharts> tMaterialCharts = convertToMaterial(materialData);
        //2-2、大于零走更新逻辑，否则走新增逻辑
        if (num > 0) {
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
        } else {
            this.saveBatch(tMaterialCharts);
        }
        return new ResultVO(0, "物料信息保存成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<MaterialChartsDTO> getMaterialCode(GainMaterialCodeDTO gainMaterialCodeDTO) {
        // 查询第一个条件的matnr
        LambdaQueryWrapper<TMaterialCharts> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.select(TMaterialCharts::getMatnr)
                .eq(TMaterialCharts::getAtnam, "S34")//固定效率标识
                .eq(TMaterialCharts::getAtwrt, gainMaterialCodeDTO.getEta());
        List<String> matnrs1 = this.list(queryWrapper1)
                .stream()
                .map(TMaterialCharts::getMatnr)
                .collect(Collectors.toList());

        // 查询第二个条件的matnr
        LambdaQueryWrapper<TMaterialCharts> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.select(TMaterialCharts::getMatnr)
                .eq(TMaterialCharts::getAtnam, "S35")//固定等级标识
                .eq(TMaterialCharts::getAtwrt, gainMaterialCodeDTO.getGrade());
        List<String> matnrs2 = this.list(queryWrapper2)
                .stream()
                .map(TMaterialCharts::getMatnr)
                .collect(Collectors.toList());
        // 求交集
        Set<String> commonMatnr = matnrs1.stream()
                .filter(matnrs2::contains)
                .collect(Collectors.toSet());

        //所有符合效率和等级的物料编码,经过和bom里面的物料编码，工单号匹配后只剩下了唯一一个
        List<String> list = commonMatnr.stream().collect(Collectors.toList());
        if (null == list || list.isEmpty()) {
            return new ResultVO(1, "查询无此物料信息", null);
        }
        String materialCode = iBomService.getTrueMaterialCode(list, gainMaterialCodeDTO.getWorkOrder());
        if (null == materialCode || "".equals(materialCode)) {
            return new ResultVO(1, "查询没有符合该工单号的物料信息", null);
        }
        LambdaQueryWrapper<TMaterialCharts> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(TMaterialCharts::getMatnr, materialCode);
        List<TMaterialCharts> tMaterialChartsList = materialChartsMapper.selectList(queryWrapper3);
        MaterialChartsDTO materialChartsDTO = new MaterialChartsDTO();
        materialChartsDTO.setMaterialCode(materialCode);
        for (TMaterialCharts tMaterialCharts : tMaterialChartsList) {
            switch (tMaterialCharts.getAtnam()) {
                case "S10":
                    materialChartsDTO.setCustomRequire(tMaterialCharts.getAtwrt());
                    break;
                case "S11A":
                    materialChartsDTO.setElePerformanceNew(tMaterialCharts.getAtwrt());
                    break;
                case "S11":
                    materialChartsDTO.setElePerformance(tMaterialCharts.getAtwrt());
                    break;
                case "S31":
                    materialChartsDTO.setEleLength(tMaterialCharts.getAtwrt());
                    break;
                case "S33":
                    materialChartsDTO.setEleLine(tMaterialCharts.getAtwtb());
                    break;
                case "S34":
                    materialChartsDTO.setEleRate(tMaterialCharts.getAtwrt());
                    break;
                case "S35":
                    materialChartsDTO.setEleLevel(tMaterialCharts.getAtwrt());
                    break;
                case "S36":
                    materialChartsDTO.setEleType(tMaterialCharts.getAtwrt());
                    break;
                case "S38":
                    materialChartsDTO.setEleManufacturer(tMaterialCharts.getAtwrt());
                    break;
                case "S3A":
                    materialChartsDTO.setType1(tMaterialCharts.getAtwtb());
                    break;
                case "S3C":
                    materialChartsDTO.setTraceInfo(tMaterialCharts.getAtwrt());
                    break;
                case "S3D":
                    materialChartsDTO.setTraceCode(tMaterialCharts.getAtwrt());
                    break;
                case "S3E":
                    materialChartsDTO.setTraceType(tMaterialCharts.getAtwrt());
                    break;
                case "S296":
                    materialChartsDTO.setProductType(tMaterialCharts.getAtwrt());
                    break;
                case "S297":
                    materialChartsDTO.setProductType2(tMaterialCharts.getAtwrt());
                    break;
                case "S298":
                    materialChartsDTO.setElePlace(tMaterialCharts.getAtwrt());
                    break;
                case "S299":
                    materialChartsDTO.setEleBrand(tMaterialCharts.getAtwrt());
                    break;
                case "S420":
                    materialChartsDTO.setEleSide(tMaterialCharts.getAtwrt());
                    break;
                default:
                    break;
            }
        }
        return new ResultVO(0, "物料信息获取成功", materialChartsDTO);
    }

    private List<TMaterialCharts> convertToMaterial(MaterialData materialData) {
        List<Charts> list = materialData.getCharts();
        List<TMaterialCharts> tMaterialChartsList = new ArrayList<>();
        for (Charts charts : list) {
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
