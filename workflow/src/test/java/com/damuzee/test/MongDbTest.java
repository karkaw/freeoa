package com.damuzee.test;


import org.damuzee.mongo.MongoTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by K.K on 2014/11/24.
 */
@ContextConfiguration(locations = { "classpath:webApplicationCntent.xml","classpath:application-content.xml" })
public class MongDbTest  extends AbstractJUnit4SpringContextTests {

    @Autowired
    MongoTemplate template;

    @Test
    public void testFindById (){
        Map map = new HashMap();
        map.put("_id","5472c737a33edbb05c377af3");
        Map m = template.findOne("template",map);
        assert (m != null);
    }
}
