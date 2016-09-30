package com.fakebank.repository;

import com.fakebank.domain.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Javan on 9/17/16.
 */
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

}
