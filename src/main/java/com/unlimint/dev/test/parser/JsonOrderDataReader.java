package com.unlimint.dev.test.parser;

import com.unlimint.dev.test.model.OrderInfo;
import com.unlimint.dev.test.util.OrderDataKeyMapperUtil;
import com.unlimint.dev.test.util.Utility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
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

@Qualifier("JsonOrderDataReader")
@Component
public class JsonOrderDataReader implements OrderDataReader {

    @Autowired
    private JSONParser jsonParser;

    @Override
    public List<OrderInfo> parseOrderDataFile(String filePath){

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try{
            String fileName = Utility.extractFileName(filePath);
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            AtomicInteger recordNumber = new AtomicInteger(1);

            StringBuffer jsonStr = new StringBuffer();
            String line = "";

            while((line = bufferedReader.readLine()) != null){
                jsonStr.append(line);
            }
            return parseOrderJsonArray(jsonStr.toString()).stream().map(this::jsonObjectValueMapper)
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

    private List<JSONObject> parseOrderJsonArray(String orderDataStr) {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(orderDataStr);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                jsonObjectList.add(jsonObject);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObjectList;
    }

    private Map<String , Object> jsonObjectValueMapper(JSONObject jsonObject){
        Map<String , Object> jsonKeyValue = new HashMap<>();
        jsonObject.entrySet().stream().forEach(jsonKeyValueEntry -> {
            Map.Entry entry = (Map.Entry) jsonKeyValueEntry;
            jsonKeyValue.put(OrderDataKeyMapperUtil.jsonColumnKeyMapper.get(String.valueOf(entry.getKey()).trim()), entry.getValue());
        });
        return jsonKeyValue;
    }
}
