package kosmachevskiy.presentation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Konstantin Kosmachevskiy
 */
@Data
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String name;
    private String category;
    private long count;
    private float price;
}
