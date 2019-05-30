package com.example.consumer.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.service.DinnerReadService;
import com.example.providerApi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DinnerCache {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DinnerReadService dinnerReadService;

    @Cached(name = "DinnerCache:findById", key = "#dinnerId", cacheType = CacheType.BOTH)
    public DinnerDTO findById(Long dinnerId) throws Exception {

        Response<DinnerDTO> dinnerResp = dinnerReadService.findById(dinnerId);
        if (dinnerResp.isSuccess()) {
            return dinnerResp.getResult();
        }
        throw new Exception("error");
    }
}
