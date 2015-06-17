package SalesReport.Beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class Product{

	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Long productId;
	
	public String productName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="productId")
	public List<SalesEntry> salesEntries;
}
