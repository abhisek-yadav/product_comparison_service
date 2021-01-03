package com.rel.pcs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rel.pcs.model.Product;
import com.rel.pcs.response.ErrorResponse;
import com.rel.pcs.response.ErrorResponse.ErrorResponseBuilder;
import com.rel.pcs.service.ProductService;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping(path = "/api/products/{productID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getProduct(@PathVariable(value = "productID") String productID) {

    if (Strings.isBlank(productID)) {
      ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.BAD_REQUEST)
          .setMessage("product_id must not blank")
          .build();
      return new ResponseEntity<>(errorResponse, null, errorResponse.getStatus());
    }

    Product product = productService.getProduct(productID);

    if (product == null) {
      ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.NOT_FOUND)
          .setMessage("product not found")
          .build();
      return new ResponseEntity<>(errorResponse, null, errorResponse.getStatus());
    }

    return ResponseEntity.ok(product);
  }

  @GetMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllProducts(@RequestParam(value = "product_name") String productName,
      @RequestParam(value = "product_category") String productCategory) {

    return ResponseEntity.ok(productService.getAllProducts(productName, productCategory));
  }

  @PostMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {

    String productID = productService.addProduct(product);

    if (productID == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    return ResponseEntity.created(URI.create(productID)).build();
  }

  @PutMapping(path = "/api/products/{productID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateProduct(@PathVariable("productID") String productID,
      @Valid @RequestBody Product product) {

    if (Strings.isBlank(productID)) {
      ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.BAD_REQUEST)
          .setMessage("product_id must not blank")
          .build();
      return new ResponseEntity<>(errorResponse, null, errorResponse.getStatus());
    }

    Product existingProduct = productService.getProduct(productID);

    if (existingProduct == null) {
      ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.NOT_FOUND)
          .setMessage("product not found")
          .build();
      return new ResponseEntity<>(errorResponse, null, errorResponse.getStatus());
    }

    product.setProductID(productID);
    productService.updateProduct(product);

    return ResponseEntity.ok().build();

  }

  @DeleteMapping(path = "/api/products/{productID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteProduct(@PathVariable(value = "productID") String productID) {

    if (Strings.isBlank(productID)) {
      ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.BAD_REQUEST)
          .setMessage("product_id must not blank")
          .build();
      return new ResponseEntity<>(errorResponse, null, errorResponse.getStatus());
    }

    Product product = productService.getProduct(productID);

    if (product == null) {
      ErrorResponse errorResponse = new ErrorResponseBuilder(HttpStatus.NOT_FOUND)
          .setMessage("product not found")
          .build();
      return new ResponseEntity<>(errorResponse, null, errorResponse.getStatus());
    }

    boolean isDeleted = productService.deleteProduct(productID);

    if (isDeleted) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @PostMapping(path = "/api/products/upload")
  public ResponseEntity<List<Product>> uploadFile(@RequestPart("file") MultipartFile file)
      throws IOException {

    if (null == file.getOriginalFilename()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    ObjectMapper mapper = new ObjectMapper();
    List<Product> products = mapper.readValue(file.getBytes(),
        mapper.getTypeFactory().constructCollectionType(List.class, Product.class));

    return ResponseEntity.ok(productService.batchUpload(products));
  }

}
