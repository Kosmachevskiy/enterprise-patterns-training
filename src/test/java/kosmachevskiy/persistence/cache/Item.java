package kosmachevskiy.persistence.cache;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Konstantin Kosmachevskiy
 */
@Component("Item")
public class Item implements ItemInterface {

    @Cacheable
    public String test(@Key String query) {
        return new Random().nextDouble() + "";
    }

    @Cacheable
    public String test2(String query, @Key String s) {
        return new Random().nextDouble() + "";
    }

    @Cacheable
    public String test3() {
        return new Random().nextDouble() + "";
    }
}
