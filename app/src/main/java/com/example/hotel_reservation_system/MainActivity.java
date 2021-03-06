package com.example.hotel_reservation_system;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotel_reservation_system.view.HotelSearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start from search fragment
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, new HotelSearchFragment());

        fragmentTransaction.commit();

    }
}