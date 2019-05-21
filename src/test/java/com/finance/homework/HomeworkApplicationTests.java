package com.finance.homework;

import com.finance.homework.repository.LoanRepository;
import com.finance.homework.repository.UserRepository;
import com.finance.homework.services.LoanService;
import com.finance.homework.services.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkApplicationTests {

	@Autowired
	Environment env;

	@Value("${loan.interest.normal}")
	String yearInterest;

	@Mock
	LoanRepository loanRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	UserService userService;

	@Mock
	LoanService loanService;

	@Test
	public void test1() {
        System.out.println("asd");
        assert(true);
	}

	@Test
	@Ignore
	public void test2() {

//		List<LoanEntity> loans =  loanRepository.getLoanApplicationsByAttempts(3);
//		System.out.println(">>   "+loans);
//		List<Long> users = loanRepository.getLoanApplicationsByAttempts(3).stream()
//				.map(loan -> loan.getUserEntity().getId())
//				.collect(Collectors.toList());
	}

	@Test
	public void test3() {
		BigDecimal result = loanService.calculateDebt(new BigDecimal(100000), 100);

		System.out.println(">>  >>  >> "+result);

	}
}
