package com.example.demo.controller;

import com.example.demo.dto.ResultVO;
import com.example.demo.dto.WorkOrderTotal;
import com.example.demo.service.IMaterialChartsService;
import com.example.demo.service.IWorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Tag(name = "WorkOrder", description = "工单数据")
public class WorkOrderController {
    @Resource
    private IWorkOrderService iWorkOrderService;


    @Operation(summary = "保存工单信息", description = "更新相同物料编码，新增不同的物料信息")
    @PostMapping("/workOrder/receiveWorkOrder")
    public ResultVO receiveWorkOrder(@RequestBody WorkOrderTotal workOrderTotal) {
        return iWorkOrderService.saveWorkOrder(workOrderTotal);
    }

    /**
     * 获取工单号集合
     */
    @Operation(summary = "获取所有工单列", description = "获取所有工单列")
    @PostMapping("/workOrder/getWorkOrderList")
    public List<String> getWorkOrderList() {
        return iWorkOrderService.getWorkOrderList();
    }
}