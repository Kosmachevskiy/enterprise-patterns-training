package kosmachevskiy.persistence;

import javax.sql.DataSource;

/**
 * @author Konstantin Kosmachevskiy
 */

abstract class TableGateway {

    private DataSource jdbcTemplate;

    TableGateway(DataSource dataSource) {
        this.jdbcTemplate = dataSource;
    }

    DataSource getDataSource() {
        return jdbcTemplate;
    }
}
