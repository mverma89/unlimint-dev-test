package com.unlimint.dev.test.parser;

import com.unlimint.dev.test.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OrderInputParser {

    @Qualifier("JsonOrderDataReader")
    @Autowired
    private OrderDataReader jsonOrderDataReader;

    @Qualifier("CsvOrderDataReader")
    @Autowired
    private OrderDataReader csvOrderDataReader;

    public void parseOrderInput(String... filesPath){

        List<CompletableFuture<List<OrderInfo>>> completableFutureList = new ArrayList<>();

        for (String filePath: filesPath) {
            if(Objects.nonNull(filePath) && filePath.lastIndexOf(".csv") != -1){
                completableFutureList.add(CompletableFuture.supplyAsync(() -> csvOrderDataReader.parseOrderDataFile(filePath)));
            }
            else if(Objects.nonNull(filePath) && filePath.lastIndexOf(".json") != -1){
                completableFutureList.add(CompletableFuture.supplyAsync(() -> jsonOrderDataReader.parseOrderDataFile(filePath)));
            }
        }

        completableFutureList.stream().map(CompletableFuture::join)
                .flatMap(list -> list.stream()).collect(Collectors.toList())
                .forEach(output -> System.out.println(output));
    }
}
