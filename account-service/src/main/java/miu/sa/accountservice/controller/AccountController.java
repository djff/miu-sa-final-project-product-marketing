package miu.sa.accountservice.controller;

import miu.sa.accountservice.model.entity.Customer;
import miu.sa.accountservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/customer")
public class AccountController {

    private final CustomerService service;

    public AccountController(CustomerService service) {
        this.service = service;
    }


    @GetMapping("login/{email}")
    public ResponseEntity<?> findAccountByEmail(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok().body(service.findByEmail(email));
    }

    @PostMapping("create")
    public ResponseEntity<?> addAccount(@RequestBody Customer account) {
        return ResponseEntity.ok(service.save(account));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findAccountById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body("holla");
    }

//    @PutMapping("update/{id}")
//    public ResponseEntity<AccountDto> updateAccount(@PathVariable(value = "id") UUID id,
//                                                    @RequestBody Customer account) {
//        return ResponseEntity.ok(service.update(id, account));
//
//    }

    //    @GetMapping
//    public ResponseEntity<List<AccountDto>> findAll() {
//        log.info("findAll controller");
//        return ResponseEntity.ok(service.findAll());
//    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
//        service.delete(id);
//        return ResponseEntity.ok().build();
//    }

//    @PutMapping("deactivate/{id}")
//    public ResponseEntity<AccountDto> deactivateAccount(@PathVariable UUID id) {
//        return ResponseEntity.ok(service.deactivate(id));
//    }

//    @PutMapping("activate/{id}")
//    public ResponseEntity<AccountDto> activateAccount(@PathVariable UUID id) {
//        return ResponseEntity.ok(service.activate(id));
//    }

}
