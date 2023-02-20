package com.similarapp.springboot.domain.ports.inbound;

import com.similarapp.springboot.domain.model.ProductDetail;

/**
 * IProductService
 * 
 * @author mjpol
 *
 */
public interface IProductService {

	/**
	 * findhSimilarProductIdsByProductId
	 * 
	 * @param productId
	 * @return Integer[]
	 */
	Integer[] findSimilarProductIdsByProductId(String productId);
	
	/**
	 * findProductById
	 * 
	 * @param productId
	 * @return CompletableFuture ProductDetail
	 */
	ProductDetail findProductById(String productId);
	
}
