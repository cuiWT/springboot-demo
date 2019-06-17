package com.example.consumer.behavior;

import com.example.providerApi.point.Behavior;
import com.example.providerApi.point.PromotionBrick;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@PromotionBrick(key = "send-integration")
public class SendIntegrationBehavior implements Behavior {
    @Override
    public String execute(Long userId, Map<String, String> behaviorParams) {
        if (userId != null){
            log.error("取出behaviorParams中integration给userId加上");
        }
        return "integration";
    }
}
