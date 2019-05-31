package com.example.providerApi.service;

import com.example.providerApi.dto.PromotionPointDTO;
import com.example.providerApi.util.Response;

public interface PromotionPointWriteService {


    Response<Boolean> save(PromotionPointDTO promotionPointDTO);

    Response<Boolean> deleteById(Long id);
}
