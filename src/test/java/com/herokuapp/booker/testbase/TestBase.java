package com.herokuapp.booker.testbase;

import com.herokuapp.booker.utils.PropertyReader;
import com.herokuapp.booker.utils.TestUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class TestBase extends TestUtils {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init(){
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
    }

}
