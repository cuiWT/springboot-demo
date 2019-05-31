package com.example.providerApi.service;

import com.example.providerApi.dto.PromotionPointDefDTO;
import com.example.providerApi.util.Response;

public interface PromotionPointDefWriteService {

    Response<Boolean> save(PromotionPointDefDTO promotionPointDefDTO);

    Response<Boolean> deleteById(Long id);
}
