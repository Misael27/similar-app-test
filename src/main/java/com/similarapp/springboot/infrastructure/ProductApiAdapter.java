package com.similarapp.springboot.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.similarapp.springboot.domain.model.ProductDetail;
import com.similarapp.springboot.domain.ports.outbound.IProductApi;

/**
 * ProductApiAdapter
 * 
 * @author mjpol
 *
 */
@Component
public class ProductApiAdapter implements IProductApi {

	RestTemplate restTemplate;
	
	public ProductApiAdapter(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Integer[] fetchSimilarProductIdsByProductId(String productId) {
		return restTemplate.getForObject("/product/" + productId + "/similarids", Integer[].class);	
	}

	@Override
	public ProductDetail fetchProductById(String productId) {
		return restTemplate.getForObject("/product/" + productId, ProductDetail.class);	
	}
	
}
