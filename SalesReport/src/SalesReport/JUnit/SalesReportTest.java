package SalesReport.JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import SalesReport.SalesReport;
import SalesReport.Beans.Product;
import SalesReport.Beans.SalesEntry;

public class SalesReportTest {

	@Test
	public void test() {
		SalesReport sr = new SalesReport();
		assertNotNull(sr.getSessionFactory());
		List<Product> testProducts = sr.createSampleData(new String[]{"test product 1", "test product 2"});
		assertEquals(2, testProducts.size());		
		Long saleCount = 0l;
		Double saleAmount = 0.0;
		for(Product testProduct : testProducts) {
			for(SalesEntry testSaleEntry : testProduct.salesEntries) {
				++saleCount;
				saleAmount+= testSaleEntry.saleAmount;
			}
		}
		List testResults = sr.getAllData();
		assertEquals(2, testResults.size());

		Long dbSaleCount = 0l;
		Double dbSaleAmount = 0.0;
		for(Object objArray : testResults) {
			Object[] datapoints=(Object[])objArray;
			dbSaleCount+=(Long)datapoints[1];
			dbSaleAmount+=(Double)datapoints[2];
		}		
		
		
		assertEquals(saleCount, dbSaleCount);
		assertEquals(saleAmount, dbSaleAmount);
	}

}
