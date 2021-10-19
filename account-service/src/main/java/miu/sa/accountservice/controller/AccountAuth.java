package miu.sa.accountservice.controller;

import miu.sa.accountservice.service.AffiliateService;
import miu.sa.accountservice.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AccountAuth {

    private final CustomerService service;
    private final AffiliateService affiliateService;

    public AccountAuth(CustomerService service, AffiliateService affiliateService) {
        this.service = service;
        this.affiliateService = affiliateService;
    }

//    @PostMapping("customer/auth")
//    public ResponseEntity<?> authenticate(@RequestBody Customer account) {
//        ResponseModel response = service.auth(account);
//        if (response.isSuccess()) {
//            return ResponseEntity.ok().body(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }
//
//    @PostMapping("affiliate/auth")
//    public ResponseEntity<?> authenticate2(@RequestBody Affiliate account) {
//        ResponseModel response = affiliateService.auth(account);
//        if (response.isSuccess()) {
//            return ResponseEntity.ok().body(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }

}
