package kosmachevskiy.persistence;

import kosmachevskiy.persistence.cache.Cacheable;
import kosmachevskiy.presentation.dto.ProductDTO;
import kosmachevskiy.presentation.dto.ProductFilterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProductTableGatewayImpl extends TableGateway implements ProductTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTableGatewayImpl.class);

    @Autowired
    ProductTableGatewayImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @Cacheable
    public List<ProductDTO> getAll() {

        LOGGER.debug("Getting all the products...");

        ArrayList<ProductDTO> list = new ArrayList<>();

        try {
            Connection connection = getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM product;");
            while (resultSet.next()) {
                list.add(map(resultSet));
            }

            statement.close();
            connection.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Cacheable
    public long getProductCategoryIdByProductId(long productId) {
        try {
            Connection connection = getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT category_id FROM product WHERE id = " + productId);
            resultSet.next();
            long categoty_id = resultSet.getLong("category_id");

            statement.close();
            connection.close();

            return categoty_id;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private ProductDTO map(ResultSet set) throws SQLException {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(set.getLong("id"));
        productDTO.setCount(set.getLong("count"));
        productDTO.setName(set.getString("name"));
        productDTO.setPrice(set.getFloat("price"));

        return productDTO;
    }

    @Override
    @Cacheable
    public List<ProductDTO> getByFilter(ProductFilterDTO filter) {

        LOGGER.debug("Getting by Filter...");

        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        try {
            Connection connection = getDataSource().getConnection();
            Statement statement = connection.createStatement();

            StringBuilder builder = new StringBuilder("SELECT * FROM product");


            // Building request start //
            builder.append(" WHERE price > " + filter.getPriceFrom());

            if (filter.getPriceTo() > 0)
                builder.append(" AND price < " + filter.getPriceTo());

            if (filter.getCategories().size() > 0) {
                List<Long> categories = filter.getCategories();
                builder.append(" AND (");
                for (Long category : categories) {
                    builder.append(" category_id = ");
                    builder.append(category);
                    // Append OR in case of it is not last item //
                    if (!category.equals(categories.get(categories.size() - 1)))
                        builder.append(" OR");
                }
                builder.append(")");
            }

            if (!filter.getNameLike().isEmpty())
                builder.append(" AND LOWER(name) LIKE '%" + filter.getNameLike().toLowerCase() + "%'");

            builder.append(";");
            // Building request end //

            ResultSet resultSet = statement.executeQuery(builder.toString());

            while (resultSet.next())
                productDTOS.add(map(resultSet));

            statement.close();
            connection.close();
            return productDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
