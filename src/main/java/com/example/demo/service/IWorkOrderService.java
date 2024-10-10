package com.example.demo.service;

import com.example.demo.dto.ResultVO;
import com.example.demo.dto.WorkOrderTotal;
import org.springframework.stereotype.Service;

@Service
public interface IWorkOrderService {

    /**
     * 新增或者更新工单数据（以工单编码为id，对于里面的bom信息，我单独放在bomservice里面处理吧）
     */
    ResultVO saveWorkOrder(WorkOrderTotal workOrderTotal);
}