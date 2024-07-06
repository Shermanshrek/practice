package crud.model;

import crud.entity.ProductEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer article;

    private String productName;

    private String description;

    private String category;

    private int price;

    private int quantity;


    public static Product toModel(ProductEntity entity){
        Product model = new Product();
        model.setArticle(entity.getArticle());
        model.setProductName(entity.getProductName());
        model.setCategory(entity.getCategory());
        model.setDescription(entity.getDescription());
        model.setPrice(entity.getPrice());
        model.setQuantity(entity.getQuantity());
        return model;
    }
}
