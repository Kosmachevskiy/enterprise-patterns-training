package kosmachevskiy.domain;

import kosmachevskiy.persistence.ProductCategoryTableGateway;
import kosmachevskiy.presentation.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
@Service
public class ProductCategory {

    @Autowired
    private ProductCategoryTableGateway gateway;

    public List<ProductCategoryDTO> getAll() {
        return gateway.getAll();
    }
}
