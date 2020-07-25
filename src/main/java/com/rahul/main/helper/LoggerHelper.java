package com.rahul.main.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LoggerHelper {
    private static boolean root=false;

    public static Logger getLogger(Class cls)  {
        if(root){
            return Logger.getLogger(cls);
        }
        URL url = Thread.currentThread().getContextClassLoader().getResource("log4j.properties");
        Path path=null;
        try {
            path = Paths.get(url.toURI());
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            PropertyConfigurator.configure(path.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        root = true;
        return Logger.getLogger(cls);
    }

    public static void main(String[] args) throws URISyntaxException {
        Logger log = LoggerHelper.getLogger(LoggerHelper.class);
        log.info("logger is configured");
        log.info("logger is configured");
        log.info("logger is configured");

    }
}
