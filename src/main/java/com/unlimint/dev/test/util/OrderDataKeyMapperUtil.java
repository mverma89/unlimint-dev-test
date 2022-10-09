package com.unlimint.dev.test.util;

import com.unlimint.dev.test.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class OrderDataKeyMapperUtil {

    public static Map<String, String> csvColumnKeyMapper = new HashMap<>();
    public static Map<String, String> jsonColumnKeyMapper = new HashMap<>();

    static {
        csvColumnKeyMapper.put("Order ID", Constant.ORDER_ID);
        csvColumnKeyMapper.put("amount", Constant.AMOUNT);
        csvColumnKeyMapper.put("currency", Constant.CURRENCY);
        csvColumnKeyMapper.put("comment", Constant.COMMENT);
    }

    static {
        jsonColumnKeyMapper.put("orderId", Constant.ORDER_ID);
        jsonColumnKeyMapper.put("amount", Constant.AMOUNT);
        jsonColumnKeyMapper.put("currency", Constant.CURRENCY);
        jsonColumnKeyMapper.put("comment", Constant.COMMENT);
    }
}
