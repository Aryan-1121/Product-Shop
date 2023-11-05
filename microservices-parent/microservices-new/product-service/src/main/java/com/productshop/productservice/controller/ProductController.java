package com.productshop.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productshop.productservice.dto.ProductRequest;
import com.productshop.productservice.dto.ProductResponse;
import com.productshop.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController					// since we are exposing rest api
@RequestMapping("/api/product")
@RequiredArgsConstructor				//for final ProductService
public class ProductController {
	
	
	private final ProductService productService;
	
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
//		System.out.println("IN createProduct - controller");
		productService.createProduct(productRequest);
		return new ResponseEntity<>("saved to DB", HttpStatus.CREATED);

	}

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
//	ProductResponse will be same as Product -> including ID  bcs its the result from  DB and we will get everything from db, now its up to us to however use the output if required
	public List<ProductResponse> getAllProducts(){
		return productService.getAllProducts();
	}
}
