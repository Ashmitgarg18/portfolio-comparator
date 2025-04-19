package com.ash.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MockPortfolioRunner implements CommandLineRunner {

    @Autowired
    private MockPortfolioBuilder mockPortfolioBuilder;

    @Override
    public void run(String... args) throws Exception {
        mockPortfolioBuilder.makeMockPortfolio();
    }
}