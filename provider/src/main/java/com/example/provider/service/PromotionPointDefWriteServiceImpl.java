package com.example.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.example.provider.dao.PromotionPointDefDao;
import com.example.provider.model.Dinner;
import com.example.provider.model.PromotionPointDef;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.dto.PromotionPointDefDTO;
import com.example.providerApi.service.DinnerReadService;
import com.example.providerApi.service.PromotionPointDefWriteService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Service(interfaceClass = PromotionPointDefWriteService.class)
public class PromotionPointDefWriteServiceImpl implements PromotionPointDefWriteService{

    @Autowired
    PromotionPointDefDao promotionPointDefDao;

    @Override
    public Response<Boolean> save(PromotionPointDefDTO promotionPointDefDTO) {
        try {
            //TODO: check Args
            PromotionPointDef promotionPointDef = MapperFactory.getCopyByRefMapper()
                    .mapClass(PromotionPointDefDTO.class, PromotionPointDef.class)
                    .registerAndMap(promotionPointDefDTO, PromotionPointDef.class);
            return Response.ok(promotionPointDefDao.save(promotionPointDef));
        } catch (Exception e) {
            log.error("create promotionPointDef fail, promotionPointDef:{} cause:{}",
                    promotionPointDefDTO, Throwables.getStackTraceAsString(e));
            return Response.fail("promotionPointDef.create.fail");
        }
    }

    @Override
    public Response<Boolean> deleteById(Long id) {
        try {
            promotionPointDefDao.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            log.error("delete promotionPointDef by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("promotionPointDef.delete.fail");
        }
    }
}
