package kosmachevskiy.domain;

import kosmachevskiy.TestConfig;
import kosmachevskiy.persistence.ProductCategoryTableGatewayImpl;
import kosmachevskiy.persistence.ProductTableGatewayImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * @author Konstantin Kosmachevskiy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        TestConfig.class,
        ProductTableGatewayImpl.class,
        Product.class,
        ProductCategoryTableGatewayImpl.class})
public class ProductTest {

    @Autowired
    private Product product;

    @Test
    public void getAll() throws Exception {
        assertNotNull(product);
        System.out.println(Arrays.toString(product.getAll().toArray()));
    }

}