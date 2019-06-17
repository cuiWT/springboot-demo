package com.example.providerApi.point;

import java.util.Map;

public interface Behavior {

    String execute(Long userId, Map<String, String> behaviorParams);
}
