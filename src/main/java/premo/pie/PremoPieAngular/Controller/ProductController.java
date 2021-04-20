package premo.pie.PremoPieAngular.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import premo.pie.PremoPieAngular.Model.Product;
import premo.pie.PremoPieAngular.Repository.ProductRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {

	 

	  @Autowired
	  ProductRepository productRepository;

	 

	  @GetMapping("/products")
	  public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String description) {
	    try {
	      List<Product> products = new ArrayList<Product>();

	 

	      if (description == null)
	        productRepository.findAll().forEach(products::add);
	      else
	        productRepository.findByProductDescription(description).forEach(products::add);

	 

	      if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	 

	      return new ResponseEntity<>(products, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @GetMapping("/products/{id}")
	  public ResponseEntity<Product> getProductById(@PathVariable("id") long productid) {
	    Optional<Product> productData = productRepository.findById(productid);

	 

	    if (productData.isPresent()) {
	      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @PostMapping("/products")
	  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	    try {
	      Product _product = productRepository
	          .save(new Product(product.getDescription(), product.getSize(), product.getPrice()/*, product.isActive()*/));
	      return new ResponseEntity<>(_product, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @PutMapping("/products/{id}")
	  public ResponseEntity<Product> updateProduct(@PathVariable("id") long productid, @RequestBody Product product) {
	    Optional<Product> productData = productRepository.findById(productid);

	 

	    if (productData.isPresent()) {
	      Product _product = productData.get();
	      _product.setDescription(product.getDescription());
	      _product.setSize(product.getSize());
	      _product.setPrice(product.getPrice());
	      /*_product.setActive(product.isActive());*/
	      return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @DeleteMapping("/products/{id}")
	  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long productid) {
	    try {
	      productRepository.deleteById(productid);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @DeleteMapping("/products")
	  public ResponseEntity<HttpStatus> deleteAllProducts() {
	    try {
	      productRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	 

	  }

	 
     /*Do we want this?
	  @GetMapping("/products/active")
	  public ResponseEntity<List<Product>> findByPublished() {
	    try {
	      List<Product> products = productRepository.findByIsActive(true);

	 

	      if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(products, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
      */
	 

	}