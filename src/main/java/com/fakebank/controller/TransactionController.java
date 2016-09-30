package com.fakebank.controller;

import com.fakebank.domain.Transaction;
import com.fakebank.exception.TransactionException;
import com.fakebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Javan on 9/23/16.
 */

@RequestMapping("/transaction")
@RepositoryRestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody Transaction handleTransaction(@RequestBody Transaction transaction) throws TransactionException {
        if (!transactionService.valid(transaction))
            throw new TransactionException("Invalid Transaction");
        if (transaction.getTransactionType() == Transaction.TransactionType.DEPOSIT)
            return transactionService.deposit(transaction);
        else
            return transactionService.withdrawal(transaction);
    }

    @ExceptionHandler(TransactionException.class)
    public void handleTransactionException(TransactionException exception, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

}
