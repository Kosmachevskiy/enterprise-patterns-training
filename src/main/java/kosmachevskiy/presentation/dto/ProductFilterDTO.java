package kosmachevskiy.presentation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Konstantin Kosmachevskiy
 */
@Data
@NoArgsConstructor
public class ProductFilterDTO {
    private float priceFrom;
    private float priceTo;
    private List<Long> categories;
    private String nameLike;
}
