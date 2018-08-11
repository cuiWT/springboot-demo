package com.example.provider.serviceTest;

import com.example.provider.BaseServiceTest;
import com.example.provider.dao.DinnerDao;
import com.example.provider.model.Dinner;
import com.example.provider.service.DinnerReadServiceImpl;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.util.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class DinnerServiceTest extends BaseServiceTest {

    @InjectMocks
    private DinnerReadServiceImpl dinnerReadService;

//    @InjectMocks
//    private DinnerWriteService dinnerWriteService;

    @Mock
    private DinnerDao dinnerDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Dinner dinner = new Dinner();
        dinner.setUserName("testName");
        dinner.setNumber(1);
        dinner.setYear(2018);
        dinner.setMonth(8);
        dinner.setDay(10);
        dinner.setWeek(5);
        dinner.setStatus(1);

        when(dinnerDao.findById(1L)).thenReturn(Optional.of(dinner));

        Response<DinnerDTO> resp = dinnerReadService.findById(1L);
        Assert.assertTrue(resp.isSuccess());
    }
}
