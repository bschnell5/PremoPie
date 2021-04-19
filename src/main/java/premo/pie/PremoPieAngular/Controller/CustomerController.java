package premo.pie.PremoPieAngular.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import premo.pie.PremoPieAngular.Model.Customer;
import premo.pie.PremoPieAngular.Repository.CustomerRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class CustomerController {

	 

	  @Autowired
	  CustomerRepository customerRepository;

	 

	  @GetMapping("/customers")
	  public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String name) {
	    try {
	      List<Customer> customers = new ArrayList<Customer>();

	 

	      if (name == null)
	        customerRepository.findAll().forEach(customers::add);
	      else
	        customerRepository.findByCustomerName(name).forEach(customers::add);

	 

	      if (customers.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	 

	      return new ResponseEntity<>(customers, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @GetMapping("/customers/{id}")
	  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long customerid) {
	    Optional<Customer> customerData = customerRepository.findById(customerid);

	 

	    if (customerData.isPresent()) {
	      return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @PostMapping("/customers")
	  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	    try {
	      Customer _customer = customerRepository
	          .save(new Customer(customer.getName(), customer.getAddress(), customer.getTin(), customer.isActive()));
	      return new ResponseEntity<>(_customer, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @PutMapping("/customers/{id}")
	  public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long customerid, @RequestBody Customer customer) {
	    Optional<Customer> customerData = customerRepository.findById(customerid);

	 

	    if (customerData.isPresent()) {
	      Customer _customer = customerData.get();
	      _customer.setName(customer.getName());
	      _customer.setAddress(customer.getAddress());
	      _customer.setTin(customer.getTin());
	      _customer.setActive(customer.isActive());
	      return new ResponseEntity<>(customerRepository.save(_customer), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @DeleteMapping("/customers/{id}")
	  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long customerid) {
	    try {
	      customerRepository.deleteById(customerid);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @DeleteMapping("/customers")
	  public ResponseEntity<HttpStatus> deleteAllCustomers() {
	    try {
	      customerRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	 

	  }

	 

	  @GetMapping("/customers/active")
	  public ResponseEntity<List<Customer>> findByPublished() {
	    try {
	      List<Customer> customers = customerRepository.findByIsActive(true);

	 

	      if (customers.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(customers, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	}