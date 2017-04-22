package kosmachevskiy.persistence;

import kosmachevskiy.persistence.cache.Cacheable;
import kosmachevskiy.presentation.dto.ProductDTO;
import kosmachevskiy.presentation.dto.ProductFilterDTO;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
public interface ProductTableGateway {
    @Cacheable
    List<ProductDTO> getAll();

    @Cacheable
    long getProductCategoryIdByProductId(long productId);

    @Cacheable
    List<ProductDTO> getByFilter(ProductFilterDTO filter);
}
