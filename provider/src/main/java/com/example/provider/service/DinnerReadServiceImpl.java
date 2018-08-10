package com.example.provider.service;

import com.baidu.unbiz.easymapper.MapperFactory;
import com.example.provider.dao.DinnerDao;
import com.example.provider.model.Dinner;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.service.DinnerReadService;
import com.example.providerApi.util.Response;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DinnerReadServiceImpl implements DinnerReadService {

    @Autowired
    private DinnerDao dinnerDao;

    @Override
    public Response<DinnerDTO> findById(Long id) {
        try {
            Optional<Dinner> dinnerOp = dinnerDao.findById(id);
            if (dinnerOp.isPresent()) {
                DinnerDTO dinnerDTO = MapperFactory.getCopyByRefMapper()
                        .mapClass(Dinner.class, DinnerDTO.class)
                        .registerAndMap(dinnerOp.get(), DinnerDTO.class);

                return Response.ok(dinnerDTO);
            }
            return Response.fail("dinner.not.find");
        } catch (Exception e) {
            log.error("find dinner by id:{} fail, cause:{}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("dinner.find.fail");
        }
    }

    @Override
    public Response<List<DinnerDTO>> findByIds(List<Long> ids) {
        try {
            List<Dinner> dinners = Lists.newArrayList(dinnerDao.findAllById(ids));
            if(CollectionUtils.isEmpty(dinners)) {
                return Response.fail("dinner.find.fail");
            }
            List<DinnerDTO> dinnerDTOList = Lists.newArrayListWithCapacity(dinners.size());
            //TODO： List convert
            return Response.ok(dinnerDTOList);
        } catch (Exception e) {
            log.error("find dinner by ids:{} fail, cause:{}", ids, Throwables.getStackTraceAsString(e));
            return Response.fail("dinner.find.fail");
        }
    }
}
