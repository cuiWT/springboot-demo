package com.example.providerApi.point;

import java.util.Map;

public interface Behavior {

    void execute(Long userId, Map<String, String> behaviorParams);
}
