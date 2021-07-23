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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.hotel_reservation_system.R;

import org.jetbrains.annotations.NotNull;

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
            Bundle bundle = new Bundle();
            bundle.putString("hotel name", hotelName);
            bundle.putString("check in date", checkInDate);
            bundle.putString("check out date", checkOutDate);
            bundle.putString("number of guests", numberOfGuests);

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
