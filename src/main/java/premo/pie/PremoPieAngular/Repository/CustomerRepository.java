package premo.pie.PremoPieAngular.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import premo.pie.PremoPieAngular.Model.Customer;



public interface CustomerRepository extends JpaRepository <Customer, Long> 
{
	  List<Customer> findByCustomerId(long customerid);
	   
	  List<Customer> findByCustomerName(String name);
	  
	  List<Customer> findByZip(int zip);
}
