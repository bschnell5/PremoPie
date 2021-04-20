package premo.pie.PremoPieAngular.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import premo.pie.PremoPieAngular.Model.CustomerOrder;



public interface CustomerOrderRepository extends JpaRepository <CustomerOrder, Long> 
{
	  List<CustomerOrder> findByCustomerOrderId(long customerOrderid);
	   
	  List<CustomerOrder> findByCustomerOrderItemOrder(String itemorder);
	  
	  List<CustomerOrder> findByCustomerOrderTotal(int total);
	  
	  List<CustomerOrder> findByCustomerOrderOrderTimeStamp(Date ordertimestamp);
}
