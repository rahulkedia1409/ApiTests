package com.rahul.test.test;

import com.rahul.main.ApiClient;
import com.rahul.main.BaseClass;
import com.rahul.main.pojo.User;
import com.rahul.test.utils.AssertionUtils;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class Test extends BaseClass {



    @org.testng.annotations.Test
    public void testUserDetails(){
        /*
        * 1. Check If the Api is returning records for the given Api
        * */
        ApiClient apiClient = new ApiClient();
        List<User> userDetailsList = apiClient.getUserList(hosturl, userName, password);
        AssertionUtils.AssertUserDetails(userDetailsList);

        /*
        * 2. Check if the given Api returns response for a particular User
        * */
      User User_withGivenPhoneNumber= apiClient.getUser(hosturl,userName,password,userDetailsList.get(0).getPhone());
        AssertionUtils.AssertUserDetails(Arrays.asList(User_withGivenPhoneNumber));

        /*
        * 3. Negative testcase User whose data doest not exists It gives 200(Error Cdde and a Valid Message should be introduced)
        * Example 00000000000
        * */
       User InvalidUser= apiClient.getUser(hosturl,userName,password,"00000000000");
        Assert.assertNull(InvalidUser,"Record not present cannot be be returned!!!!!!!!");
    }


}
