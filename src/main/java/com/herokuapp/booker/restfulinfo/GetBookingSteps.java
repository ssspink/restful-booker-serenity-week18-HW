package com.herokuapp.booker.restfulinfo;

import com.herokuapp.booker.constants.EndPoints;
import com.herokuapp.booker.model.RestFulBookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GetBookingSteps {
    static String token;


    @Step("Get all Booking")
    public ValidatableResponse getAllBooking(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_BOOKING)
                .then()
                .statusCode(200);


    }
    @Step("Get single Id booking id :{0}")
    public ValidatableResponse  getSingleId(int bookingid){
       return SerenityRest.given().log().all()
               .pathParam("id", bookingid)
                .when()
                .header("Connection", "keep-alive")
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();




    }
    @Step("update booking with id : {0},firstName : {1},lastName : {2},totalPrice : {3},depositPaid : {4},bookingDate : {5},addNeed : {6},")
    public ValidatableResponse updateBooking(int bookingid, String token, String firstName, String lastName, int totalPrice, Boolean depositpaid,String addNeed,String dateCheckIn,String dateCheckOut){
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
                .header("Accept", "application/json")
                .header("Cookie", "token="+ token)
                .pathParam("id",  bookingid)
                .body(restFulBookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then().log().all().statusCode(200);
    }
    @Step("Deleting existing booking with id : {0} ")
    public ValidatableResponse deleteBooking(int bookingid ,String token){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token="+ token)
                .pathParam("id",  bookingid)
                .when().delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then().log().all();
    }



}
