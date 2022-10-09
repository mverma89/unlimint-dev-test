package com.unlimint.dev.test.parser;

import com.unlimint.dev.test.model.OrderInfo;

import java.util.List;

public interface OrderDataReader {

    public List<OrderInfo> parseOrderDataFile(String filePath);
}
