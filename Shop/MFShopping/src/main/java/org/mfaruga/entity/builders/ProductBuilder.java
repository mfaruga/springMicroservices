package org.mfaruga.entity.builders;

import org.mfaruga.entity.EProductTag;
import org.mfaruga.entity.Product;

public class ProductBuilder {

	private Product product = new Product();
	
	public ProductBuilder setName(String name) {
		product.setName(name);
		return this;
	}
	
	public ProductBuilder setDescription(String desc) {
		product.setDescription(desc);
		return this;
	}
	
	public ProductBuilder addProductTag(EProductTag tag) {
		product.addTag(tag);
		return this;
	}
	
	public Product build() {
		return product;
	}
	
	
}
