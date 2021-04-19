package premo.pie.PremoPieAngular.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeid;

	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;	
	
	@Column(name = "tin")
	private int tin;

	@Column(name = "active")
	private boolean active;

	public Employee() 
	{
			//constructor
	}

	public Employee(String name, String address, int tin, boolean active) 
	{
		this.name = name;
		this.address = address;
		this.tin = tin;
		this.active = active;
	}

	public long getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(long employeeid) {
		this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTin() {
		return tin;
	}

	public void setTin(int tin) {
		this.tin = tin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", name=" + name + ", address=" + address + ", tin=" + tin
				+ ", active=" + active + "]";
	}
}