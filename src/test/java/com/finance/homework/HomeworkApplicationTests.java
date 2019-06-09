package com.finance.homework;


import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeworkApplicationTests {

	@Test
    @Ignore
	public void contextLoads() {}

    @Test
    @Description("Proverka 1")
    void addition() {
        assertEquals(2, 2);
    }

    @Test
    @Description("Proverka 2")
    void addition2() {
        assertEquals(3, 3);
    }
}
