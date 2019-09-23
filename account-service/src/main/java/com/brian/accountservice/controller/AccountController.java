package com.brian.accountservice.controller;


import com.brian.accountservice.service.ServiceLayer;
import com.brian.accountservice.viewModels.AccountView;
import com.brian.accountservice.viewModels.CreditView;
import com.brian.accountservice.viewModels.WithdrawView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    ServiceLayer serviceLayer;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AccountView newAccount(@RequestBody AccountView accountView) {
        return serviceLayer.createNewAccount(accountView);
    }

    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public AccountView getBalance(@PathVariable("id") int id) {
        return serviceLayer.getBalance(id);
    }


    @RequestMapping(value = "/balance/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<AccountView> getBalanceByCustomer(@PathVariable("id") int id) {
        return serviceLayer.getBalanceByCustomer(id);
    }


    @RequestMapping(value = "/credit", method = RequestMethod.POST )
    @ResponseStatus(HttpStatus.CREATED)
    public CreditView creditAccount(@RequestBody CreditView creditView) {
        return serviceLayer.creditAccount(creditView);
    }


    @RequestMapping(value = "/withdraw", method = RequestMethod.POST )
    @ResponseStatus(HttpStatus.CREATED)
    public WithdrawView withdrawFromAccount(@RequestBody WithdrawView withdrawView) {
        return serviceLayer.withdrawFromAccount(withdrawView);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAccount(@PathVariable("id") int id) {
        return serviceLayer.deleteAccount(id);
    }




}
