package com.example.providerApi.point;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PromotionBricks {

    private Map<String, Object> registry = Maps.newHashMap();

    public void register( String key, Object brick){
        if(registry.containsKey(key)){
            throw new IllegalArgumentException("brick.key.used");
        }
        registry.put(key, brick);
    }

    public Behavior getBehavior(String key){
        if(registry.containsKey( key)){
            return  (Behavior) registry.get( key);
        }else{
            throw new IllegalArgumentException("brick.key.unknown");
        }
    }
}
