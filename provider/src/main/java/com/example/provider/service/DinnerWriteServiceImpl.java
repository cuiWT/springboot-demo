package com.example.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.example.provider.dao.DinnerDao;
import com.example.provider.model.Dinner;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.service.DinnerWriteService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Service(interfaceClass = DinnerWriteService.class)
public class DinnerWriteServiceImpl implements DinnerWriteService {

    @Autowired
    private DinnerDao dinnerDao;


    @Override
    public Response<Boolean> save(DinnerDTO dinnerDTO) {
        try {
            //TODO: check Args
            Dinner dinner = MapperFactory.getCopyByRefMapper()
                    .mapClass(DinnerDTO.class, Dinner.class)
                    .registerAndMap(dinnerDTO, Dinner.class);
            return Response.ok(dinnerDao.save(dinner) != null);
        } catch (Exception e) {
            log.error("create dinner fail, dinnerDTO cause:{}", dinnerDTO, Throwables.getStackTraceAsString(e));
            return Response.fail("dinner.create.fail");
        }
    }

    @Override
    public Response<Boolean> deleteById(Long id) {
        try {
            dinnerDao.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            log.error("delete dinner by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("dinner.delete.fail");
        }
    }
}
