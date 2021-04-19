package premo.pie.PremoPieAngular.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import premo.pie.PremoPieAngular.Model.Employee;
import premo.pie.PremoPieAngular.Repository.EmployeeRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class EmployeeController {

	 

	  @Autowired
	  EmployeeRepository employeeRepository;

	 

	  @GetMapping("/employees")
	  public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String name) {
	    try {
	      List<Employee> employees = new ArrayList<Employee>();

	 

	      if (name == null)
	        employeeRepository.findAll().forEach(employees::add);
	      else
	        employeeRepository.findByEmployeeName(name).forEach(employees::add);

	 

	      if (employees.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	 

	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @GetMapping("/employees/{id}")
	  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeid) {
	    Optional<Employee> employeeData = employeeRepository.findById(employeeid);

	 

	    if (employeeData.isPresent()) {
	      return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @PostMapping("/employees")
	  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
	    try {
	      Employee _employee = employeeRepository
	          .save(new Employee(employee.getName(), employee.getAddress(), employee.getTin(), employee.isActive()));
	      return new ResponseEntity<>(_employee, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @PutMapping("/employees/{id}")
	  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeid, @RequestBody Employee employee) {
	    Optional<Employee> employeeData = employeeRepository.findById(employeeid);

	 

	    if (employeeData.isPresent()) {
	      Employee _employee = employeeData.get();
	      _employee.setName(employee.getName());
	      _employee.setAddress(employee.getAddress());
	      _employee.setTin(employee.getTin());
	      _employee.setActive(employee.isActive());
	      return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	 

	  @DeleteMapping("/employees/{id}")
	  public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long employeeid) {
	    try {
	      employeeRepository.deleteById(employeeid);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	  @DeleteMapping("/employees")
	  public ResponseEntity<HttpStatus> deleteAllEmployees() {
	    try {
	      employeeRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	 

	  }

	 

	  @GetMapping("/employees/active")
	  public ResponseEntity<List<Employee>> findByPublished() {
	    try {
	      List<Employee> employees = employeeRepository.findByIsActive(true);

	 

	      if (employees.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 

	}