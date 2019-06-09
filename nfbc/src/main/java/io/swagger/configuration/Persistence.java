package io.swagger.configuration;

import io.swagger.model.Transaction;
import io.swagger.repositories.TransactionRepository;
import io.swagger.services.TransactionService;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@Configuration
@ComponentScan("io.swagger.repositories")
public class Persistence {
    public Persistence(TransactionService transactionService) throws ParseException {
        transactionService.LoadOnStartup();
    }
}
