package kosmachevskiy.persistence;

import kosmachevskiy.TestConfig;
import kosmachevskiy.presentation.dto.ProductDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, ProductTableGateway.class})
public class ProductTableGatewayTest {

    @Autowired
    private ProductTableGateway gateway;

    @Test
    public void getAll() throws Exception {
        Assert.assertNotNull(gateway);
        List<ProductDTO> all = gateway.getAll();
        Assert.assertEquals(8, all.size());
    }

    @Test
    public void getProductCategory() throws Exception {
        Assert.assertNotNull(gateway);
        long productCategoryIdByProductId;

        productCategoryIdByProductId = gateway.getProductCategoryIdByProductId(6);
        Assert.assertEquals(1, productCategoryIdByProductId);

        productCategoryIdByProductId = gateway.getProductCategoryIdByProductId(2);
        Assert.assertEquals(2, productCategoryIdByProductId);
    }

}