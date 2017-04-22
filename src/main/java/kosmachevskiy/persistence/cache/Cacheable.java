package kosmachevskiy.persistence.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Konstantin Kosmachevskiy
 */
@Target(value = {METHOD})
@Retention(value = RUNTIME)
public @interface Cacheable {
}
