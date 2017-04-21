package kosmachevskiy.persistence;

import kosmachevskiy.presentation.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
@Component
public class ProductCategoryTableGateway extends TableGateway {
    @Autowired
    ProductCategoryTableGateway(DataSource dataSource) {
        super(dataSource);
    }

    public String getNameById(long categoryId) {
        try {
            Connection connection = getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT name FROM product_category WHERE id = " + categoryId + ";");

            resultSet.next();
            String name = resultSet.getString("name");

            resultSet.close();
            statement.close();
            connection.close();

            return name;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<ProductCategoryDTO> getAll() {
        List<ProductCategoryDTO> list = new ArrayList<>();


        try {
            Connection connection = getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM product_category;");

            while (resultSet.next())
                list.add(map(resultSet));

            statement.close();
            connection.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private ProductCategoryDTO map(ResultSet resultSet) throws SQLException {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setName(resultSet.getString("name"));
        productCategoryDTO.setId(resultSet.getLong("id"));

        return productCategoryDTO;
    }


}
