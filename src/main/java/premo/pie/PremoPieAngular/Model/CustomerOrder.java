package premo.pie.PremoPieAngular.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customerorder")
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderid;

	@Column(name = "itemorder")
	private String itemorder;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "ordertimestamp")
	private Date ordertimestamp;
	
	public CustomerOrder() 
	{
			//constructor
	}

	public CustomerOrder(String itemorder, int total, Date ordertimestamp) 
	{
		this.itemorder = itemorder;
		this.total = total;
		this.ordertimestamp = ordertimestamp;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public String getItemorder() {
		return itemorder;
	}

	public void setItemorder(String itemorder) {
		this.itemorder = itemorder;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getOrdertimestamp() {
		return ordertimestamp;
	}

	public void setOrdertimestamp(Date ordertimestamp) {
		this.ordertimestamp = ordertimestamp;
	}

	@Override
	public String toString() {
		return "CustomerOrder [orderid=" + orderid + ", itemorder=" + itemorder + ", total=" + total
				+ ", ordertimestamp=" + ordertimestamp + "]";
	}
	
}
