package com.example.hotel_reservation_system.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotel_reservation_system.R;
import com.example.hotel_reservation_system.model.GuestModel;
import com.example.hotel_reservation_system.model.ReservationModel;
import com.example.hotel_reservation_system.repository.ReservationReposistory;

import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelGuestListDetailsFragment extends Fragment {

    View view;

    TextView hotelNameTextView, priceTextView, checkInTextView, checkOutTextView;
    Button reservationButton;

    String hotelName, hotelPrice, checkInDate, checkOutDate, numberOfGuests;
    int[] firstNamesId, lastNameId, ageId, genderId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hotelNameTextView = view.findViewById(R.id.hotel_guest_hotel_name_text_view);
        priceTextView = view.findViewById(R.id.hotel_guest_price_text_view);
        checkInTextView = view.findViewById(R.id.hotel_guest_check_in_text_view);
        checkOutTextView = view.findViewById(R.id.hotel_guest_check_out_text_view);

        reservationButton = view.findViewById(R.id.confirm_reservation_button);

        hotelName = getArguments().getString("hotel name");
        hotelPrice = getArguments().getString("hotel price");
        checkInDate = dateFormat(getArguments().getString("checkIn date"));
        checkOutDate = dateFormat(getArguments().getString("checkOut date"));
        numberOfGuests = getArguments().getString("number of guests");

        int lastItemId, guestNums;

        hotelNameTextView.setText(hotelName);
        priceTextView.setText(hotelPrice);
        checkInTextView.setText(checkInDate);
        checkOutTextView.setText(checkOutDate);

        guestNums = Integer.parseInt(numberOfGuests.replace("\"", ""));
        firstNamesId = new int[guestNums];
        lastNameId = new int[guestNums];
        ageId = new int[guestNums];
        genderId = new int[guestNums];

        firstNamesId[0] = R.id.hotel_guest_first_name_edit_text;
        lastNameId[0] = R.id.hotel_guest_last_name_edit_text;
        ageId[0] = R.id.hotel_guest_age_edit_text;
        genderId[0] = R.id.hotel_guest_gender_radio_group;

        switch (numberOfGuests) {
            case "2":
                lastItemId = addSecondGuest();
                setButtonLayout(lastItemId);
                break;
            case "3":
                lastItemId = addThirdGuest();
                setButtonLayout(lastItemId);
                break;
            case "4":
                lastItemId = addFourthGuest();
                setButtonLayout(lastItemId);
                break;
            case "5":
                lastItemId = addFifthGuest();
                setButtonLayout(lastItemId);
                break;
        }

        reservationButton.setOnClickListener(v -> {
            String firstName, lastName, age, gender;
            EditText firstNameEditText, lastNameEditText, ageEditText;
            RadioGroup genderRadioGroup;

            Bundle bundle = new Bundle();

            bundle.putString("hotel name", hotelName);
            bundle.putString("checkIn date", checkInDate);
            bundle.putString("checkOut date", checkOutDate);
            bundle.putString("number of guests", numberOfGuests);

            for (int i= 0; i<guestNums; i++) {
                firstNameEditText = view.findViewById(firstNamesId[i]);
                lastNameEditText = view.findViewById(lastNameId[i]);
                ageEditText = view.findViewById(ageId[i]);
                genderRadioGroup = view.findViewById(genderId[i]);

                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                age = ageEditText.getText().toString();

                gender = getButtonText(genderRadioGroup);

                bundle.putString("first name" + i, firstName);
                bundle.putString("last name" + i, lastName);
                bundle.putString("gender" + i, gender);
                bundle.putString("age" + i, age);
            }

            ReservationConfirmationFragment reservationConfirmationFragment = new ReservationConfirmationFragment();
            reservationConfirmationFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(HotelGuestListDetailsFragment.this);
            fragmentTransaction.replace(R.id.main_layout, reservationConfirmationFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });
    }

    private String getButtonText(RadioGroup radioGroup) {
        String text = null;

        int count = radioGroup.getChildCount();
        for (int i = 0; i< count; i ++) {
            RadioButton rb = (RadioButton)radioGroup.getChildAt(i);
            if(rb.isChecked()) {
                text = rb.getText().toString();
                i = count +1;
            }
        }

        return text;
    }

    private String dateFormat(String date) {
        String day = date.split("-")[0];
        String month = date.split("-")[1];
        String year = date.split("-")[2];

        String newDate;

        if (year.length() == 4 )
            newDate = year+"-"+month+"-"+day;
        else
            newDate = date;

        return newDate;
    }

    private void setButtonLayout(int id) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) reservationButton.getLayoutParams();
        params.topToBottom = id;
        reservationButton.requestLayout();
    }

    private int addSecondGuest() {
        TextView firstNameLabelTextView = view.findViewById(R.id.hotel_guest_first_name_label_text_view2);
        TextView lastNameLabelTextView = view.findViewById(R.id.hotel_guest_last_name_label_text_view2);
        TextView genderLabelTextView = view.findViewById(R.id.hotel_guest_gender_label_text_view2);
        TextView ageLabelTextView = view.findViewById(R.id.hotel_guest_age_label_text_view2);

        EditText firstNameEditText = view.findViewById(R.id.hotel_guest_first_name_edit_text2);
        EditText lastNameEditText = view.findViewById(R.id.hotel_guest_last_name_edit_text2);
        EditText ageEditText = view.findViewById(R.id.hotel_guest_age_edit_text2);

        RadioGroup genderRadioGroup = view.findViewById(R.id.hotel_guest_gender_radio_group2);

        firstNamesId[1] = R.id.hotel_guest_first_name_edit_text2;
        lastNameId[1] = R.id.hotel_guest_last_name_edit_text2;
        ageId[1] = R.id.hotel_guest_age_edit_text2;
        genderId[1] = R.id.hotel_guest_gender_radio_group2;

        firstNameLabelTextView.setVisibility(View.VISIBLE);
        lastNameLabelTextView.setVisibility(View.VISIBLE);
        genderLabelTextView.setVisibility(View.VISIBLE);
        ageLabelTextView.setVisibility(View.VISIBLE);
        firstNameEditText.setVisibility(View.VISIBLE);
        lastNameEditText.setVisibility(View.VISIBLE);
        ageEditText.setVisibility(View.VISIBLE);
        genderRadioGroup.setVisibility(View.VISIBLE);

        return R.id.hotel_guest_age_label_text_view2;
    }

    private int addThirdGuest() {
        addSecondGuest();

        TextView firstNameLabelTextView = view.findViewById(R.id.hotel_guest_first_name_label_text_view3);
        TextView lastNameLabelTextView = view.findViewById(R.id.hotel_guest_last_name_label_text_view3);
        TextView genderLabelTextView = view.findViewById(R.id.hotel_guest_gender_label_text_view3);
        TextView ageLabelTextView = view.findViewById(R.id.hotel_guest_age_label_text_view3);

        EditText firstNameEditText = view.findViewById(R.id.hotel_guest_first_name_edit_text3);
        EditText lastNameEditText = view.findViewById(R.id.hotel_guest_last_name_edit_text3);
        EditText ageEditText = view.findViewById(R.id.hotel_guest_age_edit_text3);

        RadioGroup genderRadioGroup = view.findViewById(R.id.hotel_guest_gender_radio_group3);

        firstNamesId[2] = R.id.hotel_guest_first_name_edit_text3;
        lastNameId[2] = R.id.hotel_guest_last_name_edit_text3;
        ageId[2] = R.id.hotel_guest_age_edit_text3;
        genderId[2] = R.id.hotel_guest_gender_radio_group3;

        firstNameLabelTextView.setVisibility(View.VISIBLE);
        lastNameLabelTextView.setVisibility(View.VISIBLE);
        genderLabelTextView.setVisibility(View.VISIBLE);
        ageLabelTextView.setVisibility(View.VISIBLE);
        firstNameEditText.setVisibility(View.VISIBLE);
        lastNameEditText.setVisibility(View.VISIBLE);
        ageEditText.setVisibility(View.VISIBLE);
        genderRadioGroup.setVisibility(View.VISIBLE);

        return R.id.hotel_guest_age_label_text_view3;
    }

    private int addFourthGuest() {
        addThirdGuest();

        TextView firstNameLabelTextView = view.findViewById(R.id.hotel_guest_first_name_label_text_view4);
        TextView lastNameLabelTextView = view.findViewById(R.id.hotel_guest_last_name_label_text_view4);
        TextView genderLabelTextView = view.findViewById(R.id.hotel_guest_gender_label_text_view4);
        TextView ageLabelTextView = view.findViewById(R.id.hotel_guest_age_label_text_view4);

        EditText firstNameEditText = view.findViewById(R.id.hotel_guest_first_name_edit_text4);
        EditText lastNameEditText = view.findViewById(R.id.hotel_guest_last_name_edit_text4);
        EditText ageEditText = view.findViewById(R.id.hotel_guest_age_edit_text4);

        RadioGroup genderRadioGroup = view.findViewById(R.id.hotel_guest_gender_radio_group4);

        firstNamesId[3] = R.id.hotel_guest_first_name_edit_text4;
        lastNameId[3] = R.id.hotel_guest_last_name_edit_text4;
        ageId[3] = R.id.hotel_guest_age_edit_text4;
        genderId[3] = R.id.hotel_guest_gender_radio_group4;

        firstNameLabelTextView.setVisibility(View.VISIBLE);
        lastNameLabelTextView.setVisibility(View.VISIBLE);
        genderLabelTextView.setVisibility(View.VISIBLE);
        ageLabelTextView.setVisibility(View.VISIBLE);
        firstNameEditText.setVisibility(View.VISIBLE);
        lastNameEditText.setVisibility(View.VISIBLE);
        ageEditText.setVisibility(View.VISIBLE);
        genderRadioGroup.setVisibility(View.VISIBLE);

        return R.id.hotel_guest_age_label_text_view4;
    }

    private int addFifthGuest() {
        addFourthGuest();

        TextView firstNameLabelTextView = view.findViewById(R.id.hotel_guest_first_name_label_text_view5);
        TextView lastNameLabelTextView = view.findViewById(R.id.hotel_guest_last_name_label_text_view5);
        TextView genderLabelTextView = view.findViewById(R.id.hotel_guest_gender_label_text_view5);
        TextView ageLabelTextView = view.findViewById(R.id.hotel_guest_age_label_text_view5);

        EditText firstNameEditText = view.findViewById(R.id.hotel_guest_first_name_edit_text5);
        EditText lastNameEditText = view.findViewById(R.id.hotel_guest_last_name_edit_text5);
        EditText ageEditText = view.findViewById(R.id.hotel_guest_age_edit_text5);

        RadioGroup genderRadioGroup = view.findViewById(R.id.hotel_guest_gender_radio_group5);

        firstNamesId[4] = R.id.hotel_guest_first_name_edit_text5;
        lastNameId[4] = R.id.hotel_guest_last_name_edit_text5;
        ageId[4] = R.id.hotel_guest_age_edit_text5;
        genderId[4] = R.id.hotel_guest_gender_radio_group5;

        firstNameLabelTextView.setVisibility(View.VISIBLE);
        lastNameLabelTextView.setVisibility(View.VISIBLE);
        genderLabelTextView.setVisibility(View.VISIBLE);
        ageLabelTextView.setVisibility(View.VISIBLE);
        firstNameEditText.setVisibility(View.VISIBLE);
        lastNameEditText.setVisibility(View.VISIBLE);
        ageEditText.setVisibility(View.VISIBLE);
        genderRadioGroup.setVisibility(View.VISIBLE);

        return R.id.hotel_guest_age_label_text_view5;
    }
}
