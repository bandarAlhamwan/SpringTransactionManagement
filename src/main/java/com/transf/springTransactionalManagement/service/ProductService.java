package com.transf.springTransactionalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.transf.springTransactionalManagement.dto.Product;
import com.transf.springTransactionalManagement.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	//@Transactional(noRollbackFor = RuntimeException.class)
	@Transactional(rollbackFor = Exception.class)
	public void saveProductInfo() throws Exception {
		//create a product
		for(int i=1 ; i<=10; i++) {
			Product product = new Product();
			product.setId(i);
			product.setName("Test Product "+ i);
			productRepo.saveProduct(product);
			
			
			// add @Transactional annotation ,  the Rollback will not work if we didn't configre Transaction manager on ProductConfig class and add @EnableTransactionManagement
			// the @Transactional annotation will create proxy design pattern for saveProductInfo()  method using spring AOP
			// but if using exception to throw error the rollback will failed, to avoid that we add param to @Transactional : @Transactional(rollbackFor = Exception.class)
			if(i==7) {
				//throw new RuntimeException("Some error occured..");
				throw new Exception("Some error occured..");
			}
		}
		
	}
}
