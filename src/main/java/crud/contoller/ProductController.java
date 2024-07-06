package crud.contoller;

import crud.exceptions.ProductAlreadyExistsException;
import crud.entity.ProductEntity;
import crud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductEntity productEntity) {
        try {

            return ResponseEntity.ok(productService.saveProduct(productEntity));
        } catch (ProductAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Exception");
        }
    }

    @GetMapping
    public ResponseEntity getOneProduct(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok().body(productService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Exception");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable Integer id) {
        try {
            ProductEntity delete = productService.findProduct(id);
            productService.deleteProduct(delete);
            return ResponseEntity.ok().body("Продукт с артикулом " + id + " удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody ProductEntity update) {
        try {
            ProductEntity prod = productService.findProduct(id);
            return ResponseEntity.ok().body(productService.updateProduct(prod, update));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Exception");
        }
    }
}
