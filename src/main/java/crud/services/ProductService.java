package crud.services;

import crud.dao.ProductDao;
import crud.exceptions.ProductAlreadyExistsException;
import crud.entity.ProductEntity;
import crud.exceptions.ProductNotFoundException;
import crud.model.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ProductService {

    private final ProductDao productDAO = new ProductDao();

    public ProductEntity findProduct(Integer id) throws ProductNotFoundException {
        return productDAO.findById(id);
    }

    public Product getOne(Integer id) throws ProductNotFoundException {
        return productDAO.getOne(id);
    }

    public ProductEntity saveProduct(ProductEntity productEntity) throws ProductAlreadyExistsException {
        return productDAO.save(productEntity);
    }
    public Integer deleteProduct(ProductEntity productEntity) {
        productDAO.delete(productEntity);
        return productEntity.getArticle();
    }

    public Product updateProduct(ProductEntity productEntity, ProductEntity update) {
        return productDAO.update(productEntity, update);
    }

    public List<ProductEntity> findAll(){
        return productDAO.findAll();
    }
}
