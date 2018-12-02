package junitCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cainiao.bn.lender.data.dao.BaseTableDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class BaseTest {
    @Test
    public void testAllInOne() {
        Assert.assertNotNull("aa");
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        BaseTableDAO baseTableDAO=(BaseTableDAO) applicationContext.getBean("baseTableDAO");
        String[] aliases = applicationContext.getAliases("baseTableDAO");
        System.out.println(aliases);
    }
}