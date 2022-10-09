package com.unlimint.dev.test.util;

public class Utility {

    public static String extractFileName(String filePath){
        return filePath.substring(filePath.lastIndexOf('/')+1);
    }
}
