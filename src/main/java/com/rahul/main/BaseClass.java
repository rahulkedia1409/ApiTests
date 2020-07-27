package com.rahul.main;

import com.rahul.main.helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class BaseClass {
    public String hosturl;
    public String userName;
    public String password;
    public static Logger logger = LoggerHelper.getLogger(BaseClass.class);

    @BeforeTest
    public void setup() {

        try {

            URL url = Thread.currentThread().getContextClassLoader().getResource("config.properties");
            Path path = null;
            path = Paths.get(url.toURI());
            InputStream inputStream = new FileInputStream(path.toString());
            Properties prop = new Properties();
            prop.load(inputStream);
            hosturl = prop.getProperty("baseUrl");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
           logger.log(Priority.INFO,"user name is::: "+userName);
           logger.log(Priority.INFO,"password is :::"+password);
           logger.log(Priority.INFO,"HOST is:::"+hosturl);

        } catch (Exception e) {
            logger.log(Priority.INFO, e.getMessage());
        }
    }


}

