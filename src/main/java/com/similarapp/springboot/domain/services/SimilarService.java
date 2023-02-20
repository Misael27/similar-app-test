package com.similarapp.springboot.domain.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Value;

import com.similarapp.springboot.common.ResourceNotFoundException;
import com.similarapp.springboot.common.UseCase;
import com.similarapp.springboot.domain.model.ProductDetail;
import com.similarapp.springboot.domain.ports.inbound.IProductService;
import com.similarapp.springboot.domain.ports.inbound.ISimilarService;

/**
 * SimilarService
 * 
 * @author mjpol
 *
 */
@UseCase
public class SimilarService implements ISimilarService {
	
	private IProductService productService;
	
	@Value("${thread.parallelism}")
	private int parallelism;
	
	public SimilarService(IProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public List<ProductDetail> FindSimilarProductByProductId(String productId) {
		final List<Integer> productIds = Arrays.asList(productService.findSimilarProductIdsByProductId(productId));
		final List<ProductDetail> productResult = getProductDetailByProductIds(productIds);
		productResult.sort((p1, p2) -> Integer.valueOf(p1.getId()).compareTo(Integer.valueOf(p2.getId())) );
		return productResult;
	}
	
	/**
	 * getProductDetailByProductIds
	 * 
	 * @param productIds
	 * @return
	 */
	private List<ProductDetail> getProductDetailByProductIds(List<Integer> productIds) {
		List<ProductDetail> productResult = new ArrayList<ProductDetail>();
		ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
		try {
		    forkJoinPool.submit(() -> productIds.parallelStream().forEach(id -> {
		         final ProductDetail productDetail = this.productService.findProductById(id.toString());
		         synchronized(productResult) {
		        	 productResult.add(productDetail);
		         }
		    })).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Product Not found");
		} finally {
		    if (forkJoinPool != null) {
		        forkJoinPool.shutdown();
		    }
		}
		return productResult;
	}
	
}
