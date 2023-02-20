package com.similarapp.springboot.domain.services;

import com.similarapp.springboot.common.ResourceNotFoundException;
import com.similarapp.springboot.common.UseCase;
import com.similarapp.springboot.domain.model.ProductDetail;
import com.similarapp.springboot.domain.ports.inbound.IProductService;
import com.similarapp.springboot.domain.ports.outbound.IProductApi;

@UseCase
public class ProductService implements IProductService {

	private IProductApi productApi;
	
	public ProductService(IProductApi productApi) {
		this.productApi = productApi;
	}
	
	@Override
	public Integer[] findSimilarProductIdsByProductId(String productId) {
		try {
			return productApi.fetchSimilarProductIdsByProductId(productId);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Product Not found");
		}
	}

	@Override
	public ProductDetail findProductById(String productId) {
		try {
			return productApi.fetchProductById(productId);	
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Product Not found");
		}
	}

}
