package com.example.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.example.provider.dao.PromotionPointDao;
import com.example.provider.model.PromotionPoint;
import com.example.providerApi.dto.PromotionPointDTO;
import com.example.providerApi.service.PromotionPointWriteService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Service(interfaceClass = PromotionPointWriteService.class)
public class PromotionPointWriteServiceImpl implements PromotionPointWriteService{

    @Autowired
    PromotionPointDao promotionPointDao;

    @Override
    public Response<Boolean> save(PromotionPointDTO promotionPointDTO) {
        try {
            //TODO: check Args
            PromotionPoint promotionPoint = MapperFactory.getCopyByRefMapper()
                    .mapClass(PromotionPointDTO.class, PromotionPoint.class)
                    .registerAndMap(promotionPointDTO, PromotionPoint.class);
            return Response.ok(promotionPointDao.save(promotionPoint));
        } catch (Exception e) {
            log.error("create promotionPoint fail, promotionPoint:{} cause:{}",
                    promotionPointDTO, Throwables.getStackTraceAsString(e));
            return Response.fail("promotionPoint.create.fail");
        }
    }

    @Override
    public Response<Boolean> deleteById(Long id) {
        try {
            promotionPointDao.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            log.error("delete promotionPoint by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("promotionPoint.delete.fail");
        }
    }
}
