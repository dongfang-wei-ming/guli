package com.example.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cms.entity.CrmBanner;
import com.example.cms.service.CrmBannerService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-09
 */
@RestController
@RequestMapping("/educms/crm-banner-admin")
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{pageno}/{pagesize}")
    public R pageBanner(@PathVariable Long pageno,
                        @PathVariable Long pagesize){
        Page<CrmBanner> page = new Page<>(pageno,pagesize);
        IPage<CrmBanner> page1 = crmBannerService.page(page, null);
        return R.success().data("items",page1.getRecords()).data("total",page1.getTotal());
    }

    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.success();
    }
    @GetMapping("getBanner/{id}")
    public R getBanner(@PathVariable String id){
        CrmBanner byId = crmBannerService.getById(id);
        return R.success().data("item",byId);
    }

    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.success();
    }

    @DeleteMapping("{id}")
    public R deleteBanner(@PathVariable String id){
        crmBannerService.removeById(id);
        return R.success();
    }
}

