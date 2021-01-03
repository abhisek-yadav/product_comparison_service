package com.rel.pcs.dao.impl;

import com.rel.pcs.model.Product;
import com.rel.pcs.util.Constants;
import com.rel.pcs.dao.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Product getProduct(String productID) {

    AtomicReference<Product> product = new AtomicReference<>();

    jdbcTemplate.query(Constants.GET_PRODUCT, rs -> {
      if (!rs.next()) {
        return null;
      }
      product.set(new Product(rs.getString("PRODUCT_ID"), rs.getString("PRODUCT_NAME"),
          rs.getString("PRODUCT_CATEGORY"), rs.getFloat("PRODUCT_PRICE")));
      return product;
    }, productID);

    return product.get();
  }

  @Override
  public List<Product> getAllProducts(String productName, String productCategory) {

    List<Product> products = new ArrayList<>();

    jdbcTemplate.query(String
        .format(Constants.GET_ALL_PRODUCTS_WITH_NAME_AND_CATEGORY, "%", productName, "%", "%",
            productCategory, "%"), (rs, num) -> {
      Product product = new Product(rs.getString("PRODUCT_ID"), rs.getString("PRODUCT_NAME"),
          rs.getString("PRODUCT_CATEGORY"), rs.getFloat("PRODUCT_PRICE"));
      products.add(product);

      return products;
    });

    return products;

  }

  @Override
  public String createProduct(Product product) {

    String productID = UUID.randomUUID().toString();

    int res = jdbcTemplate.update(Constants.CREATE_PRODUCT,
        productID, product.getProductName(), product.getProductCategory(),
        product.getProductPrice());

    if (res == 1) {
      return productID;
    } else {
      return null;
    }
  }

  @Override
  public void updateProduct(Product product) {

    jdbcTemplate
        .update(Constants.UPDATE_PRODUCT, product.getProductName(), product.getProductCategory(),
            product.getProductPrice(), product.getProductID());

  }

  @Override
  public boolean deleteProduct(String productID) {
    int res = jdbcTemplate.update(Constants.DELETE_PRODUCT, productID);

    return res == 1;
  }

  @Override
  public List<Product> batchUpload(List<Product> products) {

    products.forEach(p -> {
      String productID = UUID.randomUUID().toString();
      p.setProductID(productID);
    });

    int[][] res = jdbcTemplate.batchUpdate(
        Constants.CREATE_PRODUCT,
        products,
        products.size(),
        (ps, p) -> {
          ps.setString(1, p.getProductID());
          ps.setString(2, p.getProductName());
          ps.setString(3, p.getProductCategory());
          ps.setFloat(4, p.getProductPrice());
        });

    if (res[0].length == products.size()) {
      return products;
    }

    return new ArrayList<>();
  }

  @Override
  public void batchUploadWithProductID(List<Product> products) {

    jdbcTemplate.batchUpdate(
        Constants.CREATE_PRODUCT,
        products,
        products.size(),
        (ps, p) -> {
          ps.setString(1, p.getProductID());
          ps.setString(2, p.getProductName());
          ps.setString(3, p.getProductCategory());
          ps.setFloat(4, p.getProductPrice());
        });

  }

}
