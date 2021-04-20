package premo.pie.PremoPieAngular.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import premo.pie.PremoPieAngular.Model.CustomerOrder;
import premo.pie.PremoPieAngular.Repository.CustomerOrderRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class CustomerOrderController {

	 

	  @Autowired
	  CustomerOrderRepository customerOrderRepository;

	 

	  @GetMapping("/customerOrders")
	  public ResponseEntity<List<CustomerOrder>> getAllCustomerOrders(@RequestParam(required = false) String itemorder) {
	    try {
	      List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();

	 

	      if (itemorder == null)
	        customerOrderRepository.findAll().forEach(customerOrders::add);
	      else
	        customerOrderRepository.findByCustomerOrderItemOrder(itemorder).forEach(customerOrders::add);

	 

	      if (customerOrders.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	 

	      return new ResponseEntity<>(customerOrders, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @GetMapping("/customerOrders/{id}")
	  public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable("id") long customerOrderid) {
	    Optional<CustomerOrder> customerOrderData = customerOrderRepository.findById(customerOrderid);

	 

	    if (customerOrderData.isPresent()) {
	      return new ResponseEntity<>(customerOrderData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @PostMapping("/customerOrders")
	  public ResponseEntity<CustomerOrder> createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
	    try {
	      CustomerOrder _customerOrder = customerOrderRepository
	          .save(new CustomerOrder(customerOrder.getItemorder(), customerOrder.getTotal(), customerOrder.getOrdertimestamp()));
	      return new ResponseEntity<>(_customerOrder, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @PutMapping("/customerOrders/{id}")
	  public ResponseEntity<CustomerOrder> updateCustomerOrder(@PathVariable("id") long customerOrderid, @RequestBody CustomerOrder customerOrder) {
	    Optional<CustomerOrder> customerOrderData = customerOrderRepository.findById(customerOrderid);

	 

	    if (customerOrderData.isPresent()) {
	      CustomerOrder _customerOrder = customerOrderData.get();
	      _customerOrder.setItemorder(customerOrder.getItemorder());
	      _customerOrder.setTotal(customerOrder.getTotal());
	      _customerOrder.setOrdertimestamp(customerOrder.getOrdertimestamp());
	      return new ResponseEntity<>(customerOrderRepository.save(_customerOrder), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @DeleteMapping("/customerOrders/{id}")
	  public ResponseEntity<HttpStatus> deleteCustomerOrder(@PathVariable("id") long customerOrderid) {
	    try {
	      customerOrderRepository.deleteById(customerOrderid);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @DeleteMapping("/customerOrders")
	  public ResponseEntity<HttpStatus> deleteAllCustomerOrders() {
	    try {
	      customerOrderRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	 

	  }

	 
	  /*
	  @GetMapping("/customerOrders/active")
	  public ResponseEntity<List<CustomerOrder>> findByPublished() {
	    try {
	      List<CustomerOrder> customerOrders = customerOrderRepository.findByIsActive(true);

	 

	      if (customerOrders.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(customerOrders, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 */

	}