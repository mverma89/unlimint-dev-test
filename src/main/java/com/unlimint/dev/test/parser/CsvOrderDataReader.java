package com.unlimint.dev.test.parser;

import com.unlimint.dev.test.model.OrderInfo;
import com.unlimint.dev.test.util.OrderDataKeyMapperUtil;
import com.unlimint.dev.test.util.Utility;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Qualifier("CsvOrderDataReader")
@Component
public class CsvOrderDataReader implements OrderDataReader {

    private static final String DELIMITER = ",";

    @Override
    public List<OrderInfo> parseOrderDataFile(String filePath){

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try{
            String fileName = Utility.extractFileName(filePath);
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            final String[] csvKey = bufferedReader.readLine().split(DELIMITER);
            AtomicInteger recordNumber = new AtomicInteger(1);
            return bufferedReader.lines().map(line -> line.split(DELIMITER)).map(values -> parseOrderCsvLine(csvKey, values))
                    .map(recordKeyValue -> OrderDataParser.parseOrderData(recordKeyValue, recordNumber.getAndIncrement(), fileName)).collect(Collectors.toList());
        }
        catch (IOException e){
        }
        finally {
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                }
            }

            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
        return new ArrayList<OrderInfo>();
    }

    private Map<String , Object> parseOrderCsvLine(String[] keys, String[] values){
        Map<String , Object> csvKeyValue = new HashMap<>();

        for(int i=0; i<keys.length; i++){
            csvKeyValue.put(OrderDataKeyMapperUtil.csvColumnKeyMapper.get(keys[i].trim()), values[i]);
        }
        return csvKeyValue;
    }
}
