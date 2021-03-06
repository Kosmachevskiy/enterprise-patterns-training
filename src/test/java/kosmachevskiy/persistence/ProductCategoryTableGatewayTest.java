package kosmachevskiy.persistence;

import kosmachevskiy.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author Konstantin Kosmachevskiy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, ProductCategoryTableGatewayImpl.class})
public class ProductCategoryTableGatewayTest {

    @Autowired
    private ProductCategoryTableGateway gateway;

    @Test
    public void getIdToNameMap() throws Exception {
        Assert.assertNotNull(gateway);


        Assert.assertEquals("Sneakers", gateway.getNameById(1l));
        Assert.assertEquals("Shoes", gateway.getNameById(2l));
        Assert.assertEquals("Stools", gateway.getNameById(3l));
    }

    @Test
    public void getAll() throws Exception {
        System.out.println(Arrays.toString(gateway.getAll().toArray()));
    }
}