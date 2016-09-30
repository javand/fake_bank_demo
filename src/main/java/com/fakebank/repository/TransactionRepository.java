package com.fakebank.repository;

import com.fakebank.domain.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Javan on 9/17/16.
 */
@RepositoryRestResource
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long> {

}
