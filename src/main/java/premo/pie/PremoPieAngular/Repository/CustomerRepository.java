package premo.pie.PremoPieAngular.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import premo.pie.PremoPieAngular.Model.Customer;



public interface CustomerRepository extends JpaRepository <Customer, Long> 
{
	  List<Customer> findByEmployeeId(long employeeid);
	   
	  List<Customer> findByEmployeeName(String name);
	  
	  List<Customer> findByIsActive(Boolean active);
}
