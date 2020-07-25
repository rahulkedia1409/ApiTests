package com.rahul.test.utils;

import com.rahul.main.pojo.User;
import org.testng.Assert;

import java.util.List;

public class AssertionUtils {


    /*
    * If database connection is available a get call can be validated with db call.
    *
    * */
    public static void AssertUserDetails(List<User> userList) {
        for (User user : userList) {
            Assert.assertNotNull(user.getFirst_name(), "First Name is null::");
            Assert.assertNotNull(user.getLast_name(), "Last Name is null::");
            Assert.assertNotNull(user.getCareer(), "Career is null:::");
            Assert.assertNotNull(user.getPhone(), "Phone is null:::");
        }
    }
}
