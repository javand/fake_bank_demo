package com.fakebank.service;

import com.fakebank.domain.Transaction;

/**
 * Created by Javan on 9/23/16.
 */
public interface TransactionService {

    Transaction deposit (Transaction transaction);

    Transaction withdrawal(Transaction transaction);

    boolean valid(Transaction transaction);
}
