package com.example.providerApi.service;

import com.example.providerApi.dto.PromotionPointDefDTO;
import com.example.providerApi.point.Behavior;
import com.example.providerApi.point.PromotionTool;
import com.example.providerApi.util.Response;

import java.util.List;

public interface PromotionPointDefReadService {

    Response<PromotionPointDefDTO> findById(Long id);

    Response<List<PromotionPointDefDTO>> findByIds(List<Long> ids);

    Response<PromotionTool<? extends Behavior>> findBehaviorTool(Long id);
}
