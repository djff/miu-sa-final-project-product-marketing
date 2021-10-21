package edu.miu.inventoryservice.miusamsproductatega.controller;

import com.google.gson.Gson;
import edu.miu.inventoryservice.miusamsproductatega.kafakamessaging.Producer;
import edu.miu.inventoryservice.miusamsproductatega.kafakamessaging.Consumer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.miu.inventoryservice.miusamsproductatega.dto.ProductDTO;
import edu.miu.inventoryservice.miusamsproductatega.entity.Product;
import edu.miu.inventoryservice.miusamsproductatega.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/api/products")
@AllArgsConstructor
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private Producer producer;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable UUID productId) {
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {

            return new ResponseEntity(product, HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)

    public ResponseEntity creat(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping
    public ResponseEntity<String> updateMany(@RequestBody ManyProduct manyProduct) {
        manyProduct.getProductOrders().forEach(
                productOrder -> productService.reduce(productOrder.getProductId(), productOrder.getQuantity())
        );
        return ResponseEntity.ok("Successfully updated products");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable UUID productId, @RequestBody Product product) {
        return ResponseEntity.accepted().body(productService.save(product));
    }

//   @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//   public ResponseEntity<Object> delete(@PathVariable UUID productId) {
//        productService.deleteById(productId);
//        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
  //  }
//@GetMapping()
//    public ResponseEntity<Product> findByName(@PathVariable String name) {
//        Optional<Product> product = productService.findByName( Strname);
//        if (product.isPresent()) {
//            return new ResponseEntity(product, HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping(value="products/{name}")
    public List<ProductDTO> findByName(@PathVariable String name) {
        List<ProductDTO> products = productService.findByName(name);
        return products;
    }
    Gson g = new Gson();

    @PostMapping("publish")
    public ResponseEntity<?> publish(@RequestBody  Object message) {
        producer.sendMessage(g.toJson(message));
        return ResponseEntity.ok().build();
    }
//    @RequestMapping(value = "lastname/{lastname}", method = RequestMethod.GET)
//    public List<Product> findByName(@PathVariable("lastname") String name) {
//        return productService.findByName(name);
//    }
@DeleteMapping(value = "products/{id}")
public ResponseEntity delete(@PathVariable UUID id) {
    productService.delete(id);
    return new ResponseEntity("Product is deleted successsfully", HttpStatus.OK);
}
}





