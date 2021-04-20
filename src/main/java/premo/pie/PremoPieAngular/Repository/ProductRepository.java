package premo.pie.PremoPieAngular.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import premo.pie.PremoPieAngular.Model.Product;



public interface ProductRepository extends JpaRepository <Product, Long> 
{
	  List<Product> findByProductId(long productid);
	   
	  List<Product> findByProductDescription(String description);
	  
	  List<Product> findByProductSize(String size);
	  
	  List<Product> findByProductPrice(Double price);
}
