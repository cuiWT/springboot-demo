package com.example.consumerTest.controllerTest;

import com.example.consumer.controller.Dinners;
import com.example.consumerTest.BaseControllerTest;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.service.DinnerReadService;
import com.example.providerApi.util.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class DinnersTest extends BaseControllerTest {

    @InjectMocks
    private Dinners dinners;

    @Mock
    private DinnerReadService dinnerReadService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() throws Exception {
        DinnerDTO dinner = new DinnerDTO();
        dinner.setUserName("testName");
        dinner.setNumber(1);
        dinner.setYear(2018);
        dinner.setMonth(8);
        dinner.setDay(10);
        dinner.setWeek(5);
        dinner.setStatus(1);

        when(dinnerReadService.findById(1L)).thenReturn(Response.ok(dinner));

        DinnerDTO resp = dinners.find(1L);
        Assert.assertNotNull(resp);
    }
}
