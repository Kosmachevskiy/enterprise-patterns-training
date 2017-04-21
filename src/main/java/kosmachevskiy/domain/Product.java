package kosmachevskiy.domain;

import kosmachevskiy.persistence.ProductCategoryTableGateway;
import kosmachevskiy.persistence.ProductTableGateway;
import kosmachevskiy.presentation.dto.ProductDTO;
import kosmachevskiy.presentation.dto.ProductFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */

@Service
public class Product {

    @Autowired
    private ProductTableGateway productTableGateway;
    @Autowired
    private ProductCategoryTableGateway productCategoryTableGateway;

    public List<ProductDTO> getAll() {

        List<ProductDTO> all = productTableGateway.getAll();

        injectCategories(all);

        return all;
    }

    private void injectCategories(List<ProductDTO> all) {
        for (ProductDTO productDTO : all) {
            long productCategoryId = productTableGateway.getProductCategoryIdByProductId(productDTO.getId());
            String productCategory = productCategoryTableGateway.getNameById(productCategoryId);
            productDTO.setCategory(productCategory);
        }
    }

    public List<ProductDTO> getByFilter(ProductFilterDTO filter) {
        List<ProductDTO> byFilter = productTableGateway.getByFilter(filter);
        injectCategories(byFilter);
        return byFilter;
    }
}

