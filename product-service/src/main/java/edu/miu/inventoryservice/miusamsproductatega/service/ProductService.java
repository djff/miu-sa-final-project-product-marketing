package edu.miu.inventoryservice.miusamsproductatega.service;

import edu.miu.inventoryservice.miusamsproductatega.repository.ProductRepository;
import edu.miu.inventoryservice.miusamsproductatega.entity.Product;
import edu.miu.inventoryservice.miusamsproductatega.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository ){
        this.productRepository = productRepository;
    }

    public ProductService() {

    }

//    @Cacheable(value = "products")
    public List<Product> findAll(){
        return  productRepository.findAll();
    }

    @Cacheable(value=" product", key = "#productId")
    public Optional<Product> findById(UUID productId){
        return productRepository.findById(productId);
    }

    public Product save(Product stock){
        stock.setProductId(UUID.randomUUID());
        return productRepository.save(stock);
    }

   // @CacheEvict(cacheNames="products", key = "#productId")
    //public void deleteById(UUID productId){
       //  productRepository.deleteById(productId);
   // }


//    @CacheEvict(value = "product", key = "#productId")
//    public void deleteById(@PathVariable("productId") UUID productId) {
//        productRepository.deleteById(productId);
//    }

    public ProductDTO addMore(ProductDTO productDTO) {
        Product product = productRepository.save( productDTO.toEntity() );
    return ProductDTO.of( product );}

    public ProductDTO update(UUID productId, ProductDTO productDTO) {Product product = productDTO.toEntity();
        product.setProductId( productId );
        Product updatedProduct = productRepository.save(product);
        return ProductDTO.of( updatedProduct );
    }
    public void addProductQuantity(Product product){
        product.setQuantity(product.getQuantity() + 50);
        productRepository.save(product);
    }
    public void reduce(UUID productId, int quantity) {
        var product = findById(productId);
        if (product.isPresent()) {
            var curProduct = product.get();
            if (curProduct.getQuantity() - quantity < 0) {
                addProductQuantity(curProduct);
            }
            curProduct.setQuantity(curProduct.getQuantity() - quantity);
            productRepository.save(curProduct);
        }
    }
    public void increase(UUID productId, int quantity) {
        var product = findById(productId);
        if (product.isPresent()) {
            var curProduct = product.get();
            curProduct.setQuantity(curProduct.getQuantity() + quantity);
            productRepository.save(curProduct);
        }
    }
    public ProductDTO findOne(UUID productId) {
        return productRepository.findById( productId )
                .map( ProductDTO::of )
                .orElse( null );
    }
    @Cacheable(value="name", key = "productId")
    public List<ProductDTO> findByName(String name) {
        List<Product> products = productRepository.findByName( name );

       return products.stream()
                .map( ProductDTO::of )
                .collect( Collectors.toList() );
    }

//    @Autowired
//    public void setProductRepository(ProductRepository productRepository) {
//        this.productRepository = productRepository;
    //}
    @CacheEvict(cacheNames="products", key = "#productId")
    public void delete(UUID id) {
        productRepository.deleteById(id);

    }


}


