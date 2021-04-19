package premo.pie.PremoPieAngular.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import premo.pie.PremoPieAngular.Model.Employee;



public interface EmployeeRepository extends JpaRepository <Employee, Long> 
{
	  List<Employee> findByEmployeeId(long employeeid);
	   
	  List<Employee> findByEmployeeName(String name);
	  
	  List<Employee> findByIsActive(Boolean active);
}
