package com.rel.pcs.service.impl;

import com.rel.pcs.dao.ProductDAO;
import com.rel.pcs.model.Product;
import com.rel.pcs.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDAO productDAO;

  @Override
  public Product getProduct(String productID) {
    return productDAO.getProduct(productID);
  }

  @Override
  public List<Product> getAllProducts(String productName, String productCategory) {
    return productDAO.getAllProducts(productName, productCategory);
  }

  @Override
  public String addProduct(Product product) {
    return productDAO.createProduct(product);
  }

  @Override
  public void updateProduct(Product product) {
    productDAO.updateProduct(product);
  }

  @Override
  public boolean deleteProduct(String productID) {
    return productDAO.deleteProduct(productID);
  }

  @Override
  public List<Product> batchUpload(List<Product> products) {
    return productDAO.batchUpload(products);
  }
}
