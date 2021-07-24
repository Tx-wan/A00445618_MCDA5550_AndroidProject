package com.example.hotel_reservation_system.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotel_reservation_system.R;
import com.example.hotel_reservation_system.model.GuestModel;
import com.example.hotel_reservation_system.model.ReservationModel;
import com.example.hotel_reservation_system.repository.ReservationReposistory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationConfirmationFragment extends Fragment {

    View view;

    ReservationReposistory reservationReposistory;

    String hotelName, checkInDate, checkOutDate, numberOfGuests;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.confirmation_reservation_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reservationReposistory = reservationReposistory.getInstance();

        TextView result = view.findViewById(R.id.reservation_confirmation_text_view);

        List<GuestModel> guestList = new ArrayList<GuestModel>();
        GuestModel guest;
        int guestNums;
        String firstName, lastName, age, gender;

        hotelName = getArguments().getString("hotel name");
        checkInDate = getArguments().getString("checkIn date");
        checkOutDate = getArguments().getString("checkOut date");
        numberOfGuests = getArguments().getString("number of guests");
        guestNums = Integer.parseInt(numberOfGuests.replace("\"", ""));

        for (int i= 0; i<guestNums; i++) {
            firstName = getArguments().getString("first name"+i);
            lastName = getArguments().getString("checkIn date"+i);
            age = getArguments().getString("checkOut date"+i);
            gender = getArguments().getString("number of guests"+i);

            guest = new GuestModel(firstName, lastName, gender,age);

            guestList.add(guest);
        }

        ReservationModel reservation = new ReservationModel(hotelName, checkInDate, checkOutDate, guestList);

        /*reservationReposistory.getReservationConfirmService().createReservationConfirmation(reservation).enqueue(new Callback<ReservationModel>() {
            @Override
            public void onResponse(Call<ReservationModel> call, Response<ReservationModel> response) {
                Toast.makeText(getContext(), "Reservation " + response.body().getHotelName() + " created", Toast.LENGTH_SHORT).show();
                String message = response.raw().toString();
                result.setText(message);
            }

            @Override
            public void onFailure(Call<ReservationModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error Creating Reservation: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
