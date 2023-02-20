/**
 * 
 */
package com.similarapp.springboot.domain.ports.outbound;

import com.similarapp.springboot.domain.model.ProductDetail;

/**
 * @author mjpol
 *
 */
public interface IProductApi {
	
	/**
	 * fetchSimilarProductIdsByProductId
	 * 
	 * @param productId
	 * @return Integer[]
	 */
	Integer[] fetchSimilarProductIdsByProductId(String productId);
	
	/**
	 * fetchProductById
	 * 
	 * @param productId
	 * @return ProductDetail
	 */
	ProductDetail fetchProductById(String productId);

}
