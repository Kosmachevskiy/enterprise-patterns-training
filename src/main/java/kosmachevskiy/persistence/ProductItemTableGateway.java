package kosmachevskiy.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Konstantin Kosmachevskiy
 */
@Component
public class ProductItemTableGateway extends TableGateway {

    @Autowired
    ProductItemTableGateway(DataSource dataSource) {
        super(dataSource);
    }
}
