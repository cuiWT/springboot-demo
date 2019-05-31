package com.example.providerApi.service;

import com.example.providerApi.dto.PromotionPointDTO;
import com.example.providerApi.util.Response;

import java.util.List;

public interface PromotionPointReadService {


    Response<PromotionPointDTO> findById(Long id);

    Response<List<PromotionPointDTO>> findByIds(List<Long> ids);
}
