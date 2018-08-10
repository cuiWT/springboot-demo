package com.example.providerApi.service;

import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.util.Response;

import java.util.List;

public interface DinnerReadService {

    Response<DinnerDTO> findById(Long id);

    Response<List<DinnerDTO>> findByIds(List<Long> ids);

}
