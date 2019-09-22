package com.brian.frontservice.feign;

import com.brian.frontservice.dto.Account;
import com.brian.frontservice.dto.Credit;
import com.brian.frontservice.dto.Withdraw;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @RequestMapping(value = "/account/create", method = RequestMethod.POST)
    public Account newAccount(@RequestBody Account account);

    @RequestMapping(value = "/account/balance/{id}", method = RequestMethod.GET)
    public Account getBalance(@PathVariable("id") int id);


    @RequestMapping(value = "/account/balance/customer/{id}", method = RequestMethod.GET)
    public List<Account> getBalanceByCustomer(@PathVariable("id") int id);


    @RequestMapping(value = "/account/credit", method = RequestMethod.POST )
    public Credit creditAccount(@RequestBody Credit credit);


    @RequestMapping(value = "/account/withdraw", method = RequestMethod.POST )
    public Withdraw withdrawFromAccount(@RequestBody Withdraw withdraw);


    @RequestMapping(value = "/account/delete/{id}", method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable("id") int id);

}
