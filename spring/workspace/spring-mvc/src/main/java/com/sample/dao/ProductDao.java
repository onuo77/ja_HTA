package com.sample.dao;

import java.util.List;

import com.sample.vo.Product;

public interface ProductDao {

	/**
	 * 모든 상품정보를 반환한다.
	 * @return 상품정보 목록
	 */
	List<Product> getAllProducts();
	
	/**
	 * 상품번호로 상품정보를 반환한다.
	 * @param productNo 상품번호
	 * @return 상품번호로 조회한 상품정보 목록
	 */
	Product getProductByNo(int productNo);
	
}
