package com.rel.pcs.service;

import com.rel.pcs.model.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

  Product getProduct(String productID);

  List<Product> getAllProducts(String productName, String productCategory);

  String addProduct(Product product);

  void updateProduct(Product product);

  boolean deleteProduct(String productID);

  List<Product> batchUpload(List<Product> products);
}
