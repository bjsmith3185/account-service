package com.brian.frontservice.feign;

import com.brian.frontservice.dto.AccountView;
import com.brian.frontservice.dto.CreditView;
import com.brian.frontservice.dto.WithdrawView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public AccountView newAccount(@RequestBody AccountView accountView);

    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
    public AccountView getBalance(@PathVariable("id") int id);


    @RequestMapping(value = "/balance/customer/{id}", method = RequestMethod.GET)
    public List<AccountView> getBalanceByCustomer(@PathVariable("id") int id);


    @RequestMapping(value = "/credit", method = RequestMethod.POST )
    public CreditView creditAccount(@RequestBody CreditView creditView);


    @RequestMapping(value = "/withdraw", method = RequestMethod.POST )
    public WithdrawView withdrawFromAccount(@RequestBody WithdrawView withdrawView);


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable("id") int id);

}
