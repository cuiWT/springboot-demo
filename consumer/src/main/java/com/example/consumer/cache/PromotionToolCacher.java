package com.example.consumer.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.providerApi.dto.PromotionPointDefDTO;
import com.example.providerApi.point.Behavior;
import com.example.providerApi.point.PromotionBricks;
import com.example.providerApi.point.PromotionTool;
import com.example.providerApi.service.PromotionPointDefReadService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PromotionToolCacher {


    @Autowired
    PromotionBricks promotionBricks;

    @Reference(url = "dubbo://127.0.0.1:20880")
    private PromotionPointDefReadService promotionPointDefReadService;


    public Response<PromotionTool<? extends Behavior>> findBehaviorTool(Long id) {
        try {
            Response<PromotionPointDefDTO> promotionPointDefResp = promotionPointDefReadService.findById(id);
            if (!promotionPointDefResp.isSuccess()){
                return Response.fail("promotionPointDef.not.find");
            }
            PromotionPointDefDTO promotionPointDefDTO = promotionPointDefResp.getResult();

            return Response.ok(new PromotionTool<Behavior>() {
                @Override
                public Behavior behavior() {
                    String behaviorKey = promotionPointDefDTO.getExecuteKey();
                    Behavior behavior = promotionBricks.getBehavior(behaviorKey);

                    if (behavior == null) {
                        log.error("behavior key({}) not registered ", behaviorKey);
                        throw new IllegalArgumentException("brick.key.unknown");
                    }
                    return behavior;
                }
            });
        } catch (Exception e) {
            log.error("find BehaviorTool by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("PromotionPointDef.tool.find.fail");
        }
    }

}
