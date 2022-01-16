package com.example.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cms.entity.CrmBanner;
import com.example.cms.service.CrmBannerService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-09-15:00
 */
@RestController
@RequestMapping("/educms/crm-banner-front")

public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = crmBannerService.selectAll();
        return R.success().data("bannerList",list);
    }
}
