package com.example.providerApi.point;

import java.io.Serializable;

public abstract class PromotionTool<T extends Behavior> implements Serializable {
    private static final long serialVersionUID = -8280765318965939527L;

    public abstract T behavior();

}
