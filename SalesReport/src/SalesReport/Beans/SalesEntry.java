package SalesReport.Beans;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class SalesEntry {

	public SalesEntry() {
		// TODO Auto-generated constructor stub
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	public Long salesId;
	
	@ManyToOne
	//(cascade={CascadeType.PERSIST})
	@JoinColumn(name="productId")	
	public Product product;
	
	public Date dateofSale;
	
	public Double saleAmount;
}
