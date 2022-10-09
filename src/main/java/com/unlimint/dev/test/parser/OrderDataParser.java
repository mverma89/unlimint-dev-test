package com.unlimint.dev.test.parser;

import com.unlimint.dev.test.model.OrderInfo;
import com.unlimint.dev.test.constant.Constant;

import java.util.Map;
import java.util.Objects;

public class OrderDataParser {

    public static OrderInfo parseOrderData(Map<String, Object> orderData, Integer recordNumber, String fileName){

        StringBuffer result = new StringBuffer();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setLine(recordNumber);
        orderInfo.setFilename(fileName);
        parseOderId(orderData.get(Constant.ORDER_ID), result, orderInfo);
        parseAmount(orderData.get(Constant.AMOUNT), result, orderInfo);
        parseComment(orderData.get(Constant.COMMENT), result, orderInfo);

        if(result.length() == 0)
            result.append("OK");

        orderInfo.setResult(result.toString());

        return orderInfo;
    }

    private static void parseComment(Object comment, StringBuffer result, OrderInfo orderInfo){

        if(Objects.nonNull(comment)){
            orderInfo.setComment((String) comment);
        }
        else{
            if(result.length() > 0)
                result.append(", ");
            result.append(String.format("Comment is null"));
        }
    }

    private static void parseAmount(Object amount, StringBuffer result, OrderInfo orderInfo){

        if(Objects.nonNull(amount)){
            try {
                orderInfo.setAmount(Double.parseDouble( typeConversion(amount)));
            }
            catch (NumberFormatException e){
                if(result.length() > 0)
                    result.append(", ");
                result.append(String.format("Amount %s is not a number", amount));
            }
        }
        else{
            if(result.length() > 0)
                result.append(", ");
            result.append("Amount is null");
        }
    }

    private static void parseOderId(Object orderId, StringBuffer result, OrderInfo orderInfo){

        if(Objects.nonNull(orderId)){
            try {
                orderInfo.setOrderId(Integer.parseInt(typeConversion(orderId)));
                orderInfo.setId(orderInfo.getOrderId());
            }
            catch (NumberFormatException e){
                if(result.length() > 0)
                    result.append(", ");
                result.append(String.format("OrderId %s is not a number", orderId));
            }
        }
        else{
            if(result.length() > 0)
                result.append(", ");
            result.append("OrderId is null");
        }
    }

    public static String typeConversion(Object value){
        String strVal;
        if(!(value instanceof String)){
            return value.toString();
        }
        return value.toString();
    }
}
