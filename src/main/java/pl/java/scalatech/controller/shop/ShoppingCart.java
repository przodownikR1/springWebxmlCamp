package pl.java.scalatech.controller.shop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class ShoppingCart {
	private double total;
	private String product;
	private Map<String, Double> products = new HashMap<String, Double>();

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, Double> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Double> products) {
		this.products = products;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
