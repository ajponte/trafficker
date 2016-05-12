package me.upalate.trafficker.core.common.utils;

import me.upalate.trafficker.core.TraffickerCoreApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TraffickerCoreApplication.class)
public class TestUpalateLogger {
   UpalateLogger logger = UpalateLogger.getLogger(TestUpalateLogger.class);

   @Test
   public void contextLoads() {
      
   }
}
