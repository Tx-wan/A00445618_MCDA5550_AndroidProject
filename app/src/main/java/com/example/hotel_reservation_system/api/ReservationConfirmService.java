package com.example.hotel_reservation_system.api;

import com.example.hotel_reservation_system.model.ReservationModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReservationConfirmService {

    @POST("/reservationConfirmation")
    Call<ReservationModel> createReservationConfirmation(@Body ReservationModel reservation);
}
