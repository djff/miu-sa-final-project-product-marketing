package edu.miu.inventoryservice.miusamsproductatega.repository;


import edu.miu.inventoryservice.miusamsproductatega.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository repo;

    @Test
    @Rollback
    void testCreatProduct() {
        Product p = new Product();
        p.setProductId(UUID.fromString("3d78d946-31ec-11ec-8d3d-0242ac130003"));
        p.setName("Ada Adam");
        p.setVendor("Ada Inc");
        p.setQuantity(70l);
        p.setAmount(500.0);
        p.setCategory("Electronics");
        repo.save(p);
        assertNotNull(repo.findByName("Ada Adam"));
    }
@Test
public void findAll(){
    List<Product> list = repo.findAll();
    assertThat(list.size()).isGreaterThan (0);
}
//@Test
//    public void update (){
//        Product pr = repo.findById(UUID.fromString("3d78d946-31ec-11ec-8d3d-0242ac130003")).get();
//
//        pr.setQuantity(10L);
//        pr.setProductId(UUID.fromString("3d78d946-31ec-11ec-8d3d-0242ac130003"));
//        repo.save(pr);
//        assertNotEquals(80L,repo.findById(UUID).getQuantity());
//
//}
@Test
public void delete(){
        repo.deleteById(UUID.fromString("3d78d946-31ec-11ec-8d3d-0242ac130003"));
        assertThat(repo.existsById(UUID.fromString("3d78d946-31ec-11ec-8d3d-0242ac130003"))).isFalse();
}

  }