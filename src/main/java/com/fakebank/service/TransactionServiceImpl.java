package com.fakebank.service;

import com.fakebank.domain.Account;
import com.fakebank.domain.Transaction;
import com.fakebank.repository.AccountRepository;
import com.fakebank.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by Javan on 9/23/16.
 */
@Service
public class TransactionServiceImpl implements TransactionService{
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;

    }

    @Override
    @Transactional
    public Transaction deposit(Transaction transaction){
        Account account = accountRepository.findOne(transaction.getAccount().getId());
        logger.info(transaction.getAccount().getId().toString());

        BigDecimal previousBalance = account.getBalance();

        account.setBalance(previousBalance.add(transaction.getAmount()));
        accountRepository.save(account);
        transaction.setAccount(account);
        transaction.setTransactionStatus(Transaction.TransactionStatus.OK);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction withdrawal(Transaction transaction){
        Account account = accountRepository.findOne(transaction.getAccount().getId());
        BigDecimal previousBalance = account.getBalance();
        transaction.setAccount(account);
        int res = previousBalance.compareTo(transaction.getAmount());

        if (res != -1) {
            account.setBalance(previousBalance.subtract(transaction.getAmount()));
            accountRepository.save(account);
            transaction.setTransactionStatus(Transaction.TransactionStatus.OK);
        }
        else
            transaction.setTransactionStatus(Transaction.TransactionStatus.INSUFFICIENT_FUNDS);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public boolean valid(Transaction transaction){
        if (transaction.getAccount() == null)
            return false;
        Account account = accountRepository.findOne(transaction.getAccount().getId());
        if (account == null)
            return false;
        return true;
    }
}
