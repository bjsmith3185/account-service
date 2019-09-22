package com.brian.frontservice.controller;

import com.brian.frontservice.dto.Customer;
import com.brian.frontservice.service.ServiceLayer;
import com.brian.frontservice.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
@RequestMapping(value = "/transaction")
public class FrontController {


    @Autowired
    private ServiceLayer service;


    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("id") int id) {

        return service.getCustomerById(id);
    }


    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public DepositResponseView makeDeposit(@RequestBody AccoutQueryView accoutQueryView) {

        return service.makeDeposit(accoutQueryView);
    }


    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public WithdrawResponseView makeWithdraw(@RequestBody AccoutQueryView accoutQueryView) {

        return service.makeWithdraw(accoutQueryView);
    }

    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BalanceResponseView getBalanceById(@PathVariable("id") int id) {

        return service.getAccountBalalce(id);
    }


    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public NewAccountView newAccount(@RequestBody NewAccountView newAccountView) {

        return service.newAccount(newAccountView);
    }




}
