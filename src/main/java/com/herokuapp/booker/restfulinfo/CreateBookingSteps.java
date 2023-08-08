package com.herokuapp.booker.restfulinfo;

import com.herokuapp.booker.constants.EndPoints;
import com.herokuapp.booker.model.AuthorisationPojo;
import com.herokuapp.booker.model.RestFulBookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CreateBookingSteps {
    @Step("creating token : {0}")
    public ValidatableResponse createTokenId(String userName, String password){
        AuthorisationPojo authorisationPojo = new AuthorisationPojo();
        authorisationPojo.setUsername(userName);
        authorisationPojo.setPassword(password);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(authorisationPojo)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then();
    }


    @Step("Create User Booking firstName : {0},lastName : {1},totalPrice : {2},depositPaid : {3},bookingDate : {4},addNeed : {5}, ")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalPrice, Boolean depositpaid,String addNeed,String dateCheckIn,String dateCheckOut){
        RestFulBookingPojo.BookingDates date = new RestFulBookingPojo.BookingDates();
        RestFulBookingPojo restFulBookingPojo = new RestFulBookingPojo();
        date.setCheckin(dateCheckIn);
        date.setCheckout(dateCheckOut);
        restFulBookingPojo.setFirstname(firstName);
        restFulBookingPojo.setLastname(lastName);
        restFulBookingPojo.setTotalprice(totalPrice);
        restFulBookingPojo.setDepositpaid(depositpaid);
        restFulBookingPojo.setBookingdates(date);
        restFulBookingPojo.setAdditionalneeds(addNeed);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(restFulBookingPojo)
                .when()
                .post(EndPoints.GET_BOOKING)
                .then();



    }
}
