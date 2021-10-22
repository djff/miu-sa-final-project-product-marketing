package miu.sa.accountservice.service;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import miu.sa.accountservice.exceptions.ResourceNotFoundException;
import miu.sa.accountservice.model.AccountDto;
import miu.sa.accountservice.model.ResponseModel;
import miu.sa.accountservice.model.entity.Affiliate;
import miu.sa.accountservice.repository.AffiliateRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("Duplicates")
@Slf4j
public class AffiliateService {

    private final AffiliateRepository repository;

    public AffiliateService(AffiliateRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "affiliate", key = "#email")
    public ResponseModel findByEmail(String email) {
        log.info("findByEmail Request => " + email);
        ResponseModel response = new ResponseModel();

        try {
            Affiliate affiliate = repository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Affiliate with email" + email + " not found")
            );
            response.setMessage("Affiliate info fetched");
            response.setSuccess(true);
            response.setData(affiliate);
        } catch (ResourceNotFoundException e) {
            log.error("findByEmail Exception => " + e.getMessage());
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            response.setData("");
        }
        log.info("findByEmail Response => " + response);
        return response;
    }

    public ResponseModel save(Affiliate account) {
        log.info("save Request => " + account);
        ResponseModel response = new ResponseModel();
        try {
            //  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            //  account.setPassword(encoder.encode(account.getPassword()));
            account.setId(UUID.randomUUID());
            account.setActive(true);
            Affiliate acct = repository.save(account);

            response.setMessage("Affiliate created");
            response.setSuccess(true);
            response.setData(new AccountDto(acct.getId(), acct.getFirstName(), acct.getLastName(), acct.getEmail(),
                    acct.getRole(), acct.isActive(), acct.getAddresses(), acct.getPayments()));
        } catch (Exception e) {
            log.error("save Exception => " + e.getMessage());
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            response.setData("");
        }
        log.info("save Response => " + response);
        return response;
    }

    @Cacheable(value = "affiliate", key = "id")
    public ResponseModel findById(UUID id) {
        log.info("findById Request => " + id);
        ResponseModel response = new ResponseModel();
        try {
            AccountDto dto = repository.findById(id)
                    .map(a -> new AccountDto(a.getId(), a.getFirstName(), a.getLastName(), a.getEmail(), a.getRole(),
                            a.isActive(), a.getAddresses(), a.getPayments()))
                    .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found for this id :: " + id));
            response.setMessage("Customer info fetched");
            response.setSuccess(true);
            response.setData(dto);
        } catch (Exception e) {
            log.error("findById Exception => " + e.getMessage());
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            response.setData("");
        }
        log.info("findById Response => " + response);
        return response;
    }

    @Cacheable(value = "affiliates")
    public ResponseModel findAll() {
        log.info("findAll Request ");
        ResponseModel response = new ResponseModel();
        try {
            response.setMessage("Affiliate list fetched");
            response.setSuccess(true);
            response.setData(repository.findAll().stream().map(a -> new AccountDto(a.getId(), a.getFirstName(), a.getLastName(),
                    a.getEmail(), a.getRole(), a.isActive(), a.getAddresses(), a.getPayments()))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("findAll Exception => " + e.getMessage());
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            response.setData("");
        }
        log.info("findAll Response ==> " + response);
        return response;
    }

//    public ResponseModel auth(Affiliate account){
//        ResponseModel response = new ResponseModel();
//        boolean suc = repository.findByEmailAndPassword(account.getEmail(), account.getPassword())
//                .isPresent();
//        if(suc) {
//            response.setMessage("Authentication Successful");
//            response.setSuccess(true);
//            response.setData(new JsonObject());
//        }
//        else{
//            response.setMessage("Authentication Failure");
//            response.setSuccess(false);
//            response.setData(new JsonObject());
//        }
//        return response;
//    }
//
//    @CachePut(value = "affiliates", key = "id")
//    // TODO: 11/8/21 password encryption for update or create new endpoint to update password
//    public AccountDto update(UUID id, Affiliate account) {
//        Affiliate acct = repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found for this id :: " + id));
//        acct.setFirstName(account.getFirstName());
//        acct.setLastName(account.getLastName());
//        acct.setEmail(account.getEmail());
//        acct.setAddresses(account.getAddresses());
//        acct.setPayments(account.getPayments());
//        return save(acct);
//    }
//
//    @CacheEvict(value = "affiliates", key="id", allEntries=true)
//    public void delete(UUID id) {
//        Affiliate account = repository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Affiliate " + id + " not found")
//        );
//        repository.delete(account);
//    }
//
//
//    public AccountDto deactivate(UUID id) {
//        Affiliate acct = repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found for this id :: " + id));
//        acct.setActive(false);
//        return save(acct);
//    }
//
//    public AccountDto activate(UUID id) {
//        Affiliate acct = repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found for this id :: " + id));
//        acct.setActive(true);
//        return save(acct);
//    }


}
