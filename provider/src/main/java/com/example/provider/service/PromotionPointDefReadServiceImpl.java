package com.example.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.example.provider.dao.PromotionPointDefDao;
import com.example.provider.model.PromotionPointDef;
import com.example.providerApi.dto.PromotionPointDefDTO;
import com.example.providerApi.service.PromotionPointDefReadService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Service(interfaceClass = PromotionPointDefReadService.class)
public class PromotionPointDefReadServiceImpl implements PromotionPointDefReadService, Serializable {

    private static final long serialVersionUID = 2377336327214818525L;
    @Autowired
    PromotionPointDefDao promotionPointDefDao;

    @Override
    public Response<PromotionPointDefDTO> findById(Long id) {
        try {
            Optional<PromotionPointDef> promotionPointDefOp = promotionPointDefDao.findById(id);
            if (promotionPointDefOp.isPresent()) {
                PromotionPointDefDTO promotionPointDefDTO = MapperFactory.getCopyByRefMapper()
                        .mapClass(PromotionPointDef.class, PromotionPointDefDTO.class)
                        .registerAndMap(promotionPointDefOp.get(), PromotionPointDefDTO.class);

                return Response.ok(promotionPointDefDTO);
            }
            return Response.fail("promotionPointDef.not.find");
        } catch (Exception e) {
            log.error("find promotionPointDef by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("promotionPointDef.find.fail");
        }
    }

    @Override
    public Response<List<PromotionPointDefDTO>> findByIds(List<Long> ids) {
        try {

            List<PromotionPointDef> PromotionPointDefs = Lists.newArrayList(promotionPointDefDao.findAllById(ids));
            if (CollectionUtils.isEmpty(PromotionPointDefs)) {
                return Response.fail("PromotionPointDef.find.fail");
            }
            List<PromotionPointDefDTO> PromotionPointDefDTOList = Lists.newArrayListWithCapacity(PromotionPointDefs.size());
            //TODO： List convert
            return Response.ok(PromotionPointDefDTOList);
        } catch (Exception e) {
            log.error("find PromotionPointDef by ids:{} fail, cause:{}", ids, Throwables.getStackTraceAsString(e));
            return Response.fail("PromotionPointDef.find.fail");
        }
    }



}
