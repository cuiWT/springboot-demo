package com.example.providerApi.service;

import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.util.Response;

public interface DinnerWriteService {

    Response<Boolean> save(DinnerDTO dinnerDTO);

    Response<Boolean> deleteById(Long id);

}
