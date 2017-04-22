package kosmachevskiy.persistence.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Konstantin Kosmachevskiy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Item.class, CacheableAnnotationBeanPostProcessor.class})
public class CacheableAnnotationBeanPostProcessorTest {
    @Autowired
    ItemInterface item;

    @Test
    public void oneParam() throws Exception {
        Assert.assertNotNull(item);

        String test = item.test("test");
        Assert.assertEquals(test, item.test("test"));
        Assert.assertEquals(test, item.test("test"));

        String test_test = item.test("test_test");
        Assert.assertEquals(test_test, item.test("test_test"));
        Assert.assertEquals(test_test, item.test("test_test"));

        Assert.assertNotEquals(test, test_test);
        Assert.assertNotEquals(item.test("test"), item.test("test_test"));


    }

    @Test
    public void twoParams() throws Exception {
        String test = item.test2("", "test");

        Assert.assertEquals(test, item.test2("!", "test"));
        Assert.assertEquals(test, item.test2("!!", "test"));

        Assert.assertNotEquals(test, item.test2("!!", "test!"));
    }

    @Test
    public void noParams() throws Exception {
        String s = item.test3();

        Assert.assertEquals(s, item.test3());
        Assert.assertEquals(s, item.test3());

    }
}