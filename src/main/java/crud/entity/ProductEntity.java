package crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article")
    private Integer article;
    @Column(name = "category_")
    private String category;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "last_change")
    private Date lastChange;
    @Column(name = "create_date")
    private Date createDate;

    public ProductEntity(String product_name, String desc, String category, int price, int quantity, Date last_change, Date create_date) {
        this.productName = product_name;
        this.description = desc;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.lastChange = last_change;
        this.createDate = create_date;
    }
}
