package com.rel.pcs.dao;

import com.rel.pcs.model.Product;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO {

  Product getProduct(String productID);

  List<Product> getAllProducts(String productName, String productCategory);

  String createProduct(Product product);

  void updateProduct(Product product);

  boolean deleteProduct(String productID);

  List<Product> batchUpload(List<Product> products);

  void batchUploadWithProductID(List<Product> products);

}
