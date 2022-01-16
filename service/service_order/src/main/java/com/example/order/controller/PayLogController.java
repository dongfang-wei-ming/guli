package com.example.order.controller;


import com.example.order.service.PayLogService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/eduorder/paylog")

public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        Map map= payLogService.createNative(orderNo);
        return R.success().data(map);
    }

    @GetMapping("queryOrderStatus/{orderNo}")
    public R queryOrderStatus(@PathVariable String orderNo){
        Map map = payLogService.queryOrderStatus(orderNo);
        if(map == null) return R.fail().message("支付出错");
        if(map.get("trade_state").equals("SUCCESS")){
            payLogService.updateOrderStatus(map);
            return R.success().message("支付成功");
        }
        return R.success().code(25000).message("支付中");
    }
}

