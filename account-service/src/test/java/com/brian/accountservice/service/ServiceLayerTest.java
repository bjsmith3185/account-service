package com.brian.accountservice.service;

import com.brian.accountservice.dao.AccountDao;
import com.brian.accountservice.dao.AccountDaoJdbc;
import com.brian.accountservice.dto.Account;
import com.brian.accountservice.viewModels.AccountView;
import com.brian.accountservice.viewModels.CreditView;
import com.brian.accountservice.viewModels.WithdrawView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {


    AccountDao accountDao;
    ServiceLayer service;



    @Before
    public void setUp() throws Exception {

        SetUpAccountDaoMock();

        service = new ServiceLayer(accountDao);


    }

    private void SetUpAccountDaoMock() {

        accountDao = mock(AccountDaoJdbc.class);

        // Account to take in
        Account account = new Account();
        account.setBalance(200);
        account.setCustomerId(1);

        // Account to return
        Account accountFromService = new Account();
        accountFromService.setAccountId(1);
        accountFromService.setBalance(200);
        accountFromService.setCustomerId(1);

        // Additional Account to return
        Account accountFromService1 = new Account();
        accountFromService1.setAccountId(2);
        accountFromService1.setBalance(400);
        accountFromService1.setCustomerId(2);
        // account list to return
        List<Account> accountList = new ArrayList<>();
        accountList.add(accountFromService);
        accountList.add(accountFromService1);

        // credit view
        CreditView creditView = new CreditView();
        creditView.setAccountId(1);
        creditView.setBalance(200);
        creditView.setCredit(15);
        creditView.setCustomerId(1);

        // Account object for update
        Account accountUpdate = new Account();
        accountUpdate.setAccountId(1);
        accountUpdate.setBalance(215);
        accountUpdate.setCustomerId(1);

        // return credit view
        CreditView creditViewFromService = new CreditView();
        creditViewFromService.setAccountId(1);
        creditViewFromService.setBalance(215);
        creditViewFromService.setCredit(15);
        creditViewFromService.setCustomerId(1);

        // input withdrawView
        WithdrawView inputWithdrawView = new WithdrawView();
        inputWithdrawView.setAccountId(1);
        inputWithdrawView.setWithdraw(15);

        // Account object for update
        Account accountUpdateWithdraw = new Account();
        accountUpdateWithdraw.setAccountId(1);
        accountUpdateWithdraw.setBalance(185);
        accountUpdateWithdraw.setCustomerId(1);

        // return vithdrawView
        WithdrawView returnWithdrawView = new WithdrawView();
        returnWithdrawView.setAccountId(1);
        returnWithdrawView.setBalance(185);
        returnWithdrawView.setWithdraw(15);
        returnWithdrawView.setCustomerId(1);





        doReturn(accountFromService).when(accountDao).addAccount(account);
        doReturn(accountList).when(accountDao).getAllAccounts();
        doReturn(accountList).when(accountDao).getAccountByCustomer(1);
        doReturn(accountFromService).when(accountDao).getAccount(1);

        // need an update method on accoutDao, this can return nothing
        doNothing().when(accountDao).updateAccount(accountUpdate);
        doNothing().when(accountDao).updateAccount(accountUpdateWithdraw);


    }




    @Test
    public void creditAccount() {

        //create a CreditView to enter into the method
        CreditView creditView = new CreditView();
        creditView.setAccountId(1);
        creditView.setBalance(200);
        creditView.setCredit(15);
        creditView.setCustomerId(1);

        // create a creditview to check against
        CreditView updatedCreditView = new CreditView();
        updatedCreditView.setAccountId(1);
        updatedCreditView.setBalance(215);
        updatedCreditView.setCredit(15);
        updatedCreditView.setCustomerId(1);


        CreditView fromService = service.creditAccount(creditView);

        assertEquals(updatedCreditView, fromService);

    }

    @Test
    public void withdrawFromAccount() {

        WithdrawView inputWithdrawView = new WithdrawView();
        inputWithdrawView.setAccountId(1);
        inputWithdrawView.setWithdraw(15);

        // return vithdrawView
        WithdrawView returnWithdrawView = new WithdrawView();
        returnWithdrawView.setAccountId(1);
        returnWithdrawView.setBalance(185);
        returnWithdrawView.setWithdraw(15);
        returnWithdrawView.setCustomerId(1);

        WithdrawView fromService = service.withdrawFromAccount(inputWithdrawView);

        assertEquals(returnWithdrawView, fromService);


    }

    @Test
    public void getBalance() {

        AccountView fromService = service.getBalance(1);

        assertEquals(200, fromService.getBalance());
    }

    @Test
    public void getBalanceByCustomer() {

        List<AccountView> fromService = service.getBalanceByCustomer(1);

        assertEquals(200, fromService.get(0).getBalance());

    }

    @Test
    public void createNewAccount() {

        // to service
        AccountView input = new AccountView();
        input.setBalance(200);
        input.setCustomerId(1);


        // from service
        AccountView fromService = new AccountView();
        fromService.setAccountId(1);
        fromService.setBalance(200);
        fromService.setCustomerId(1);

        AccountView returnAccount = service.createNewAccount(input);

        assertEquals(fromService, returnAccount);


    }


    @Test
    public void getAllAccounts() {

        List<AccountView> fromService = service.getAllAccounts();

        assertEquals(2, fromService.size());
    }


}