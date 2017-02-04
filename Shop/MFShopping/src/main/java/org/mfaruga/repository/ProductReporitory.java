package org.mfaruga.repository;

import java.util.List;

import org.mfaruga.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// publish this interface through REST
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
//auto-generated spring repository to access/modify (CRUD) on Product object
public interface ProductReporitory extends PagingAndSortingRepository<Product, Long> {
	
	List<Product> findByNameContainsOrderByName(String name);
	
}
