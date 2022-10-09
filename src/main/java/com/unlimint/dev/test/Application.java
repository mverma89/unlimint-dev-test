package com.unlimint.dev.test;

import com.unlimint.dev.test.parser.OrderInputParser;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JSONParser.class})
public class Application implements CommandLineRunner {

    @Autowired
    private OrderInputParser orderInputParser;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderInputParser.parseOrderInput(args);
    }

}
