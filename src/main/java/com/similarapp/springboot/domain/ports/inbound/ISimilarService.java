package com.similarapp.springboot.domain.ports.inbound;

import java.util.List;

import com.similarapp.springboot.domain.model.ProductDetail;

/**
 * 
 * @author mjpol
 *
 */
public interface ISimilarService {
	
	/**
	 * FindSimilarProductByProductId
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductDetail> FindSimilarProductByProductId(String productId);
	
}
