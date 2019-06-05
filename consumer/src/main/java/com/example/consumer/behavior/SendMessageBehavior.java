package com.example.consumer.behavior;

import com.example.providerApi.point.Behavior;
import com.example.providerApi.point.PromotionBrick;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@PromotionBrick(key = "send-message")
public class SendMessageBehavior implements Behavior {
    @Override
    public void execute(Long userId, Map<String, String> behaviorParams) {
        if (userId!= null) {
            log.error("给userId发送message成功");
        }
    }
}
