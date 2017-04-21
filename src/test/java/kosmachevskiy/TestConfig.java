package kosmachevskiy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Konstantin Kosmachevskiy
 */
@Configuration
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:/home/toss/tmp/online-shop-db");
        return dataSource;
    }

    @PostConstruct
    public void init() throws SQLException {
        ScriptUtils.executeSqlScript(dataSource().getConnection(),
                new ClassPathResource("init.sql"));
    }
}
