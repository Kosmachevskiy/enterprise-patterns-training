package kosmachevskiy.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Konstantin Kosmachevskiy
 */
@Component
public class OrderTableGateway extends TableGateway {
    @Autowired
    OrderTableGateway(DataSource dataSource) {
        super(dataSource);
    }
}
