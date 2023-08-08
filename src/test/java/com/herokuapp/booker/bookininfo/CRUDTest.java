package com.herokuapp.booker.bookininfo;

import com.herokuapp.booker.restfulinfo.CreateBookingSteps;
import com.herokuapp.booker.restfulinfo.GetBookingSteps;
import com.herokuapp.booker.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class CRUDTest extends TestBase {
    static String userName = "admin";
    static String password = "password123";
    static String firstName = "jenna";
    static String lastName = "Thak";
    static Integer totalPrice = 300;
    static Boolean depositPaid = true;
    static String addNeed = "Breakfast";
    static String dateCheckIn = "2023-11-01";
    static String dateCheckOut = "2023-11-01";
    static String token;
    static int bookingid;
    @Steps
    CreateBookingSteps bookingSteps;
    @Title("create token")
    @Test
    public void test001() {
        ValidatableResponse response = bookingSteps.createTokenId(userName, password);
        response.log().all().statusCode(200);
        token = response.extract().path("token");
        System.out.println("this is for viewing : " + token);
    }
    @Title("Get All booking Id")
    @Test
    public void test002(){
        ValidatableResponse response = getSteps.getAllBooking()
                .log().all();

    }



    @Title("Create booking ")
    @Test
    public void test003() {
        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid, addNeed, dateCheckIn, dateCheckOut);
        response.log().all().statusCode(200);
        bookingid = response.extract().path("bookingid");

    }

    @Steps
    GetBookingSteps getSteps;

    @Title("Get user by single Id")
    @Test
    public void test004() {
        ValidatableResponse response = getSteps.getSingleId(bookingid);
        response.log().all().statusCode(200);
        HashMap<String,Object> value = response.extract().path("");
        Assert.assertThat(value,hasValue(firstName));

    }


    @Title("update booking ")
    @Test
    public void test005() {
        firstName = "katie";
        getSteps.updateBooking(bookingid, token, firstName, lastName, totalPrice,
                depositPaid, addNeed, dateCheckIn, dateCheckOut).log().all().statusCode(200);

        ValidatableResponse response = getSteps.getSingleId(bookingid);
        HashMap<String,Object> value = response.extract().path("");
        Assert.assertThat(value,hasValue(firstName));


    }

    @Title("Delete booking")
    @Test
    public void test006() {
        getSteps.deleteBooking(bookingid, token).statusCode(201).log().all();
        getSteps.getSingleId(bookingid).statusCode(404).log().all();
    }
}
