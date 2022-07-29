package com.transf.springTransactionalManagement;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.transf.springTransactionalManagement.config.ProductConfig;
import com.transf.springTransactionalManagement.service.ProductService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
        context.registerShutdownHook();
        
        ProductService productService = context.getBean("productService",ProductService.class);
        productService.saveProductInfo();
        context.close();
    
    }
}
