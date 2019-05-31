package com.example.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.example.provider.dao.PromotionPointDao;
import com.example.provider.model.PromotionPoint;
import com.example.providerApi.dto.PromotionPointDTO;
import com.example.providerApi.service.PromotionPointReadService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Service(interfaceClass = PromotionPointReadService.class)
public class PromotionPointReadServiceImpl implements PromotionPointReadService{
    
    @Autowired
    PromotionPointDao promotionPointDao;
    
    @Override
    public Response<PromotionPointDTO> findById(Long id) {
        try {
            Optional<PromotionPoint > promotionPointOp = promotionPointDao.findById(id);
            if (promotionPointOp.isPresent()) {
                PromotionPointDTO promotionPointDTO = MapperFactory.getCopyByRefMapper()
                        .mapClass(PromotionPoint .class, PromotionPointDTO.class)
                        .registerAndMap(promotionPointOp.get(), PromotionPointDTO.class);

                return Response.ok(promotionPointDTO);
            }
            return Response.fail("promotionPoint .not.find");
        } catch (Exception e) {
            log.error("find promotionPoint  by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("promotionPoint .find.fail");
        }    }

    @Override
    public Response<List<PromotionPointDTO>> findByIds(List<Long> ids) {
        try {

            List<PromotionPoint > PromotionPoints = Lists.newArrayList(promotionPointDao.findAllById(ids));
            if (CollectionUtils.isEmpty(PromotionPoints)) {
                return Response.fail("PromotionPoint .find.fail");
            }
            List<PromotionPointDTO> PromotionPointDTOList = Lists.newArrayListWithCapacity(PromotionPoints.size());
            //TODO： List convert
            return Response.ok(PromotionPointDTOList);
        } catch (Exception e) {
            log.error("find PromotionPoint  by ids:{} fail, cause:{}", ids, Throwables.getStackTraceAsString(e));
            return Response.fail("PromotionPoint .find.fail");
        }    }
}
