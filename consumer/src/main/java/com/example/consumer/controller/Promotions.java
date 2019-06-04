package com.example.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.providerApi.dto.PromotionPointDTO;
import com.example.providerApi.point.Behavior;
import com.example.providerApi.point.PromotionTool;
import com.example.providerApi.service.PromotionPointDefReadService;
import com.example.providerApi.service.PromotionPointReadService;
import com.example.providerApi.service.PromotionPointWriteService;
import com.example.providerApi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/promotion")
public class Promotions {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private PromotionPointWriteService promotionPointWriteService;

    @Reference(url = "dubbo://127.0.0.1:20880")
    private PromotionPointReadService promotionPointReadService;

    @Reference(url = "dubbo://127.0.0.1:20880")
    private PromotionPointDefReadService promotionPointDefReadService;

    @PostMapping("action")
    public void action(Long userId, Long promotionId) throws Exception {
        Response<PromotionPointDTO> resp = promotionPointReadService.findById(promotionId);
        if (!resp.isSuccess()) {
            log.error("获取promotion 失败。promotionId:{}, cause:{}", promotionId, resp.getError());
            throw new Exception(resp.getError());
        }
        PromotionPointDTO promotionPointDTO = resp.getResult();
        Response<PromotionTool<? extends Behavior>> toolResp = promotionPointDefReadService.findBehaviorTool(promotionId);
        if (!toolResp.isSuccess()) {
            log.error("获取 PromotionTool 失败。promotionId:{}, cause:{}", promotionId, resp.getError());
            throw new Exception(resp.getError());
        }
        final PromotionTool<? extends Behavior> promotionTool = toolResp.getResult();
        final Behavior behavior = promotionTool.behavior();
        behavior.execute(userId, new HashMap<>());

    }
}
