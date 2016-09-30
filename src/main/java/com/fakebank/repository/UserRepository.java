package com.fakebank.repository;

import com.fakebank.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Javan on 9/17/16.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

}
