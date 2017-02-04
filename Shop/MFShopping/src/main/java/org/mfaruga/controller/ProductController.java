package org.mfaruga.controller;

import java.util.ArrayList;
import java.util.List;

import org.mfaruga.entity.Product;
import org.mfaruga.repository.ProductReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class ProductController {

	@Autowired
	ProductReporitory productRepo;

	@RequestMapping(path = "/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Product> getAllProducts() {
		Iterable<Product> all = productRepo.findAll();
		ArrayList<Product> products = new ArrayList<>();
		for (Product prod : all) {
			products.add(prod);
		}
		return products;
	}

	@RequestMapping(value = "/getpaged", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<Product> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {

		System.out.println("I'm called!");
		
		Page<Product> resultPage = productRepo.findAll(new PageRequest(page, size));
		if (page > resultPage.getTotalPages()) {
			throw new ResourceNotFoundException();
		}

		return resultPage;
	}

	@RequestMapping(path = "/allnames", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<String> getAllProductNames() {
		Iterable<Product> all = productRepo.findAll();
		ArrayList<String> productNames = new ArrayList<>();
		for (Product prod : all) {
			productNames.add(prod.getName());
		}
		
		System.out.println("Returning product names: " + productNames.size());
		
		return productNames;
	}
	
	@RequestMapping(path = "/names", params = { "productName" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<String> getProductNames(@RequestParam("productName") String productName) {
		Iterable<Product> all = productRepo.findByNameContainsOrderByName(productName);
		ArrayList<String> productNames = new ArrayList<>();
		for (Product prod : all) {
			productNames.add(prod.getName());
		}
		
		System.out.println("Returning product names filtered: " + productName + " size of results: " + productNames.size());
		
		return productNames;
	}
	

	// TODO most likely it would be best to make sure that product is not the
	// same entity that is going to database
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
		System.out.println("Received request to add product " + product);
		try {
			productRepo.save(product);
		} catch (Exception e) {
			return new ResponseEntity<>("There were issues with creating product. Details: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Product created with id: " + product.getId(), HttpStatus.OK);
	}
}

// @RequestMapping(path = "/json/{id}", method = RequestMethod.GET, produces =
// "application/json")
// @ResponseBody
// public CreateTaskResponse createNewTaskJSON(@PathVariable("id") Long id) {
// Task task = new Task();
// task.setName(id.toString());
// task.setComments("Some comments");
// return new CreateTaskResponse(task);
// }
