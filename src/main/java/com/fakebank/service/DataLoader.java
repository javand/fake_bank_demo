package com.fakebank.service;

import com.fakebank.domain.Account;
import com.fakebank.domain.User;
import com.fakebank.repository.AccountRepository;
import com.fakebank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;

@Service
public class DataLoader {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, AccountRepository accountRepository){
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    private void loadData(){


        //create user
        User user = new User("College","Student","limitedfunds@education.org");
        userRepository.save(user);

        //create account
        Account account = new Account(new BigDecimal(25.00));
        account.setAccountType(Account.AccountType.CHECKING);
        account.setUser(user);
        accountRepository.save(account);


        //find an existing users with a certain last name and create accounts for them
        for (User warBucks : userRepository.findByLastName("Warbucks")){
            Account bigBucks = new Account(new BigDecimal(500.00));
            bigBucks.setAccountType(Account.AccountType.SAVINGS);
            bigBucks.setUser(warBucks);
            accountRepository.save(bigBucks);
        }
    }
}
