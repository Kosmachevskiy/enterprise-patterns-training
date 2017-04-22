package kosmachevskiy.persistence;

import kosmachevskiy.persistence.cache.Cacheable;
import kosmachevskiy.presentation.dto.ProductCategoryDTO;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
public interface ProductCategoryTableGateway {
    @Cacheable
    String getNameById(long categoryId);

    @Cacheable
    List<ProductCategoryDTO> getAll();
}
