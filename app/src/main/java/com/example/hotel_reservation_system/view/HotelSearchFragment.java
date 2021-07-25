package com.example.hotel_reservation_system.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotel_reservation_system.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    View view;
    ConstraintLayout mainLayout;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText guestsCountEditText, nameEditText;
    Button confirmSearchButton, searchButton, retrieveButton, clearButton;
    DatePicker checkInDatePicker, checkOutDatePicker;
    String checkInDate, checkOutDate, numberOfGuests, guestName;

    SharedPreferences sharedPreferences;
    public static final String myPreference = "myPref";
    public static final String name = "nameKey";
    public static final String guestsCount = "guestsCount";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_text_view);
        searchTextConfirmationTextView = view.findViewById(R.id.search_confirm_text_view);

        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text);
        nameEditText = view.findViewById(R.id.name_edit_text);

        confirmSearchButton = view.findViewById(R.id.confirm_my_search_button);
        searchButton = view.findViewById(R.id.search_button);
        retrieveButton = view.findViewById(R.id.retrieve_button);
        clearButton = view.findViewById(R.id.clear_button);

        checkInDatePicker = view.findViewById(R.id.checkin_date_picker_view);
        checkOutDatePicker = view.findViewById(R.id.checkout_date_picker_view);

        titleTextView.setText(R.string.welcome_text);

        confirmSearchButton.setOnClickListener(v -> {
            checkInDate = getDateFromCalendar(checkInDatePicker);
            checkOutDate = getDateFromCalendar(checkOutDatePicker);
            numberOfGuests = guestsCountEditText.getText().toString();
            guestName = nameEditText.getText().toString();

            int check = checkForm(guestName, numberOfGuests);
            if (check == 1) {
                searchTextConfirmationTextView.setText("Please input name and number of guests.");
            }else if ( check == 2) {
                searchTextConfirmationTextView.setText("The maximum capacity of room is 5, please place one order with less than 6 guests.");
            } else {
                sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(name, guestName);
                editor.putString(guestsCount, numberOfGuests);
                editor.commit();

                searchTextConfirmationTextView.setText("Dear Customer, Your check in date is " + checkInDate + ", " +
                        "your checkout date is " + checkOutDate + ".The number of guests are " + numberOfGuests);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfGuests = guestsCountEditText.getText().toString();
                guestName = nameEditText.getText().toString();
                int check = checkForm(guestName, numberOfGuests);
                if (check == 1) {
                    searchTextConfirmationTextView.setText("Please input name and number of guests.");
                }else if ( check == 2) {
                    searchTextConfirmationTextView.setText("The maximum capacity of room is 5, please place one order with less than 6 guests.");
                } else {
                    checkInDate = getDateFromCalendar(checkInDatePicker);
                    checkOutDate = getDateFromCalendar(checkOutDatePicker);

                    Bundle bundle = new Bundle();
                    bundle.putString("check in date", checkInDate);
                    bundle.putString("check out date", checkOutDate);
                    bundle.putString("number of guests", numberOfGuests);

                    HotelsListFragment hotelsListFragment = new HotelsListFragment();
                    hotelsListFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
                    fragmentTransaction.remove(HotelSearchFragment.this);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        retrieveButton.setOnClickListener(v -> {
            sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);

            if (sharedPreferences.contains(name)) {
                nameEditText.setText(sharedPreferences.getString(name, ""));
            }
            if (sharedPreferences.contains(guestsCount)) {
                guestsCountEditText.setText(sharedPreferences.getString(guestsCount, ""));

            }
        });

        clearButton.setOnClickListener(v -> {
            guestsCountEditText.setText("");
            nameEditText.setText("");
            searchTextConfirmationTextView.setText("");
        });
    }

    private String getDateFromCalendar(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;
    }

    private int checkForm(String guestName, String numberOfGuests) {
        int check = 0;

        if (numberOfGuests == null || numberOfGuests.equals("")) {
            check = 1;
        } else if (guestName == null || guestName.equals("")) {
            check = 1;
        } else {
            int num = Integer.parseInt(numberOfGuests.replace("\"", ""));
            if (num > 5)
                check = 2;
        }

        return check;
    }

}
