package kosmachevskiy.presentation;

import kosmachevskiy.domain.Product;
import kosmachevskiy.domain.ProductCategory;
import kosmachevskiy.presentation.dto.ProductCategoryDTO;
import kosmachevskiy.presentation.dto.ProductDTO;
import kosmachevskiy.presentation.dto.ProductFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
@RestController
public class Controller {

    @Autowired
    private Product product;
    @Autowired
    private ProductCategory category;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<ProductDTO> getAll() {
        return product.getAll();
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<ProductCategoryDTO> getAllCategories() {
        return category.getAll();
    }

    @RequestMapping(value = "/product/byFilter", method = RequestMethod.POST)
    public List<ProductDTO> getByFilter(@RequestBody ProductFilterDTO filter) {
        return product.getByFilter(filter);
    }
}
