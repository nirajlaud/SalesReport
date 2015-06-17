package SalesReport; 

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import SalesReport.Beans.Product;
import SalesReport.Beans.SalesEntry;

public class SalesReport {
	SessionFactory sf;
	
	public SalesReport() {
		AnnotationConfiguration a = new AnnotationConfiguration().addAnnotatedClass(SalesEntry.class).addAnnotatedClass(Product.class).configure();
		sf = a.buildSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sf;
	}

	public static void main(String[] args) throws Exception{
		SalesReport sr = new SalesReport();
		sr.createSampleData(new String[]{"product 1", "product 2", "product 3"});
		sr.renderReport(sr.getAllData());
	}

	
	public List<Product> createSampleData(String[] products) {
		List<Product> sampleData = new ArrayList<Product>(); 
		for(String product : products) {
			Product p = new Product();
			p.productName = product;
			p.salesEntries = new ArrayList<SalesEntry>();
			Random rn = new Random();
			int count = (rn.nextInt() % 10) + 1;
			if(count < 0 ) count = count * -1;
			for(int i =1 ; i <= count; i++) {
				SalesEntry se = new SalesEntry();
				se.saleAmount = new Double(i * count * 100);
				p.salesEntries.add(se);
			}
			sampleData.add(p);
		}
		Session s = getSessionFactory().getCurrentSession();
		org.hibernate.Transaction t = s.beginTransaction();
		for(Product p : sampleData) {
			s.persist(p);
		}
		//t.commit();
		return sampleData;
	}
	
	public List getAllData() {
		Session s = getSessionFactory().getCurrentSession();
		org.hibernate.Transaction t = s.beginTransaction();
		Query q = s.createQuery("select product.productName, count(salesId), sum(saleAmount) from SalesEntry s group by product.productName");
		return q.list();
	}
	
	public void renderReport(List report) {
		System.out.println("Product Name\t\t\tTotal # sold\t\t\tTotal Amount");
		for(Object objArray : report) {
			Object[] datapoints=(Object[])objArray;
			System.out.println(datapoints[0]+"\t\t\t"+datapoints[1]+"\t\t\t"+datapoints[2]);
		}
	}
}
