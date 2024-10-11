package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.BomDTO;
import com.example.demo.dto.ResultVO;
import com.example.demo.dto.WorkOrderDetail;
import com.example.demo.dto.WorkOrderTotal;
import com.example.demo.entity.TWorkOrder;
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
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, TWorkOrder> implements IWorkOrderService {

    @Resource
    IBomService iBomService;

    @Resource
    WorkOrderMapper workOrderMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO saveWorkOrder(WorkOrderTotal workOrderTotal) {
        log.info("工单号数据：" + workOrderTotal);
        //有两部分需要保存，一部分是工单信息(工单号也需要批量保存)，一部分是BOM信息（bom也需要批量保存）
        List<WorkOrderDetail> workOrderDetailList = workOrderTotal.getWorkOrderDetails();
        if (null == workOrderDetailList) {
            return new ResultVO(1, "工单信息为空",null);
        }
        //工单号也有存在需要更新的情况，如果出现相同的工单号，则更新，没有则新增
        for (WorkOrderDetail workOrderDetail : workOrderDetailList) {
            TWorkOrder tWorkOrder = new TWorkOrder();
            BeanUtils.copyProperties(workOrderDetail, tWorkOrder);
            LambdaUpdateWrapper<TWorkOrder> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(TWorkOrder::getAufnr, tWorkOrder.getAufnr());
            boolean updated = this.update(tWorkOrder, lambdaUpdateWrapper);
            if (!updated) {
                this.save(tWorkOrder);
            }
            List<BomDTO> bomDTOs = workOrderDetail.getBomDTO();
            for (BomDTO bomDTO : bomDTOs) {
                bomDTO.setAufnr(tWorkOrder.getAufnr());
            }
            iBomService.saveBomData(bomDTOs);
        }
        return new ResultVO(0, "工单信息保存成功",null);
    }

    @Override
    public List<String> getWorkOrderList() {
        LambdaQueryWrapper<TWorkOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(TWorkOrder::getAufnr)
                .groupBy(TWorkOrder::getAufnr);
        List<TWorkOrder> list = workOrderMapper.selectList(lambdaQueryWrapper);
        List<String> orderList = new ArrayList<>();
        for (TWorkOrder workOrder : list) {
            String order = workOrder.getAufnr();
            orderList.add(order);
        }
        return orderList;
    }
}
