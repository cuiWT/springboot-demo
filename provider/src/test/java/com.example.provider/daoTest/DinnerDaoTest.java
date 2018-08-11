package com.example.provider.daoTest;

import com.example.provider.BaseDaoTest;
import com.example.provider.dao.DinnerDao;
import com.example.provider.model.Dinner;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DinnerDaoTest extends BaseDaoTest {
    
    @Autowired
    private DinnerDao dinnerDao;
    
    private Dinner dinner;
    
    @Before
    public void init() {
        dinner = new Dinner();
        dinner.setUserName("testName");
        dinner.setNumber(1);
        dinner.setYear(2018);
        dinner.setMonth(8);
        dinner.setDay(10);
        dinner.setWeek(5);
        dinner.setStatus(1);
        
        dinnerDao.save(dinner);
        Assert.assertNotNull(dinnerDao.findById(dinner.getId()));
    }
    
    @Test
    public void findById() {
        Optional<Dinner> exist = dinnerDao.findById(dinner.getId());
        Assert.assertTrue(exist.isPresent());
    }

    @Test
    public void findByIds() {
        Iterable<Dinner> dinners = dinnerDao.findAllById(Lists.newArrayList(dinner.getId()));
        Assert.assertNotNull(dinners);
    }

    @Test
    public void testSave() {
        dinner.setUserName("updateName");
        dinnerDao.save(dinner);
        Optional<Dinner> exist = dinnerDao.findById(dinner.getId());
        Assert.assertTrue(exist.isPresent());
        Assert.assertEquals("updateName", exist.get().getUserName());
    }

    @After
    public void delete() {
        dinnerDao.deleteById(dinner.getId());
        Assert.assertTrue(!dinnerDao.findById(dinner.getId()).isPresent());
    }
}
