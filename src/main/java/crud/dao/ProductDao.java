package crud.dao;

import crud.exceptions.ProductAlreadyExistsException;
import crud.entity.ProductEntity;
import crud.exceptions.ProductNotFoundException;
import crud.model.Product;
import crud.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository("productDao")
public class ProductDao {
    public ProductEntity findById(Integer article) throws ProductNotFoundException {
        ProductEntity entity = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ProductEntity.class, article);
        if (entity == null)
            throw new ProductNotFoundException("Product not found!");
        return entity;
    }

    public Product getOne(Integer id) throws ProductNotFoundException {
        ProductEntity entity = findById(id);
        if (entity == null)
            throw new ProductNotFoundException("Product not found!");
        return Product.toModel(entity);
    }

    public ProductEntity save(ProductEntity productEntity) throws ProductAlreadyExistsException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().
                openSession();
        if (isExist(productEntity)) {
            Transaction tx1 = session.beginTransaction();
            session.persist(productEntity);
            tx1.commit();
            session.close();
        }
        return productEntity;
    }

    public Product update(ProductEntity productEntity, ProductEntity update) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        if (!notNull(update.getProductName()))
            productEntity.setProductName(update.getProductName());
        if (!notNull(update.getDescription()))
            productEntity.setDescription(update.getDescription());
        if (!notNull(update.getCategory()))
            productEntity.setCategory(update.getCategory());
        if (!notNull(update.getPrice()))
            productEntity.setPrice(update.getPrice());
        if (!notNull(update.getQuantity()))
            productEntity.setQuantity(update.getQuantity());
        productEntity.setLastChange(new Date());
        if(!notNull(update.getCreateDate()))
            productEntity.setCreateDate(update.getCreateDate());
        session.merge(productEntity);
        tx1.commit();
        session.close();
        return Product.toModel(productEntity);
    }

    public void delete(ProductEntity productEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(productEntity);
        tx1.commit();
        session.close();
    }

    public List<ProductEntity> findAll(){
        List<ProductEntity> list = (List<ProductEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM ProductEntity ").list();
        return list;
    }

    private boolean isExist(ProductEntity productEntity) throws ProductAlreadyExistsException {
        List<ProductEntity> list = findAll();
        String name = productEntity.getProductName();
        for (ProductEntity value : list) {
            if (value.getProductName().equals(name)) {
                throw new ProductAlreadyExistsException("Product already exists!");
            }
        }
        return true;
    }

    private boolean notNull(Object field){
        if (Objects.isNull(field))
            return true;
        return false;
    }
}
