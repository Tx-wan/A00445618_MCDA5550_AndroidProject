package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelsListFragment extends Fragment implements ItemClickListener {

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<HotelListData> userListResponseData;
 //   HotelListResponse userResponseData;

    HotelListViewModel hotelListViewModel;
    HotelListSearchAdapter hotelListSearchAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hotelListSearchAdapter = new HotelListSearchAdapter();

        hotelListViewModel = ViewModelProviders.of(this).get(HotelListViewModel.class);
        hotelListViewModel.init();
        hotelListViewModel.searchHotelList();

        hotelListViewModel.getHotelListLiveData().observe(this, new Observer<HotelListResponse>() {
            @Override
            public void onChanged(HotelListResponse hotelListResponse) {
                if(hotelListResponse != null) {
                    userListResponseData = hotelListResponse.getHotelListData();
                    hotelListSearchAdapter.setHotelListData(hotelListResponse.getHotelListData());
                }
            }
        });
    }

    String checkInDate;
    String checkOutDate;
    String numberOfGuests;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        checkInDate = getArguments().getString("check in date");
        checkOutDate = getArguments().getString("check out date");
        numberOfGuests = getArguments().getString("number of guests");

        headingTextView.setText("Welcome user, displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*hotelListViewModel.getHotelListLiveData().observe(getViewLifecycleOwner(), hotelList -> {
            HotelListResponse a = hotelList;
            if(hotelList!= null) {
                hotelListSearchAdapter.setHotelListData(hotelList.getHotelListData());
            }
        });*/

        recyclerView.setAdapter(hotelListSearchAdapter);
        hotelListSearchAdapter.setClickListener(this);

        progressBar.setVisibility(View.GONE);



        /*ArrayList<HotelListData> hotelListData = initHotelListData();
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListData);
        recyclerView.setAdapter(hotelListAdapter);*/
        //getHotelsListsData();
    }

/*    private void getHotelsListsData() {

        progressBar.setVisibility(View.VISIBLE);

        //
        //hotelListViewModel = new ViewModelProviders.of(this).get(HotelListViewModel.class);

        Api.getClient().getHotelsLists(new Callback<HotelList>() {
            @Override
            public void success(HotelList userListResponses, Response response) {
                userResponseData = userListResponses;

                progressBar.setVisibility(View.GONE);
                *//*RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userResponseData.getHotelListData());
                recyclerView.setAdapter(hotelListAdapter);

                hotelListAdapter.setClickListener(this);*//*

                setupRecyclerView();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }*/

 /*   private void setupRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        *//*HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userResponseData.getHotelListData());
        recyclerView.setAdapter(hotelListAdapter);
        hotelListAdapter.setClickListener(this);*//*
        //HotelListSearchAdapter hotelListAdapter = new HotelListSearchAdapter(getActivity(), userResponseData.getHotelListData());
        recyclerView.setAdapter(hotelListAdapter);
        hotelListAdapter.setClickListener(this);
    }
*/
    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = userListResponseData.get(position);

        String hotelName = hotelListData.getHotelName();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailability();

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", price);
        bundle.putString("checkIn date", checkInDate);
        bundle.putString("checkOut date", checkOutDate);
        bundle.putString("number of guests", numberOfGuests);

        HotelGuestListDetailsFragment hotelGuestListDetailsFragment = new HotelGuestListDetailsFragment();
        hotelGuestListDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelsListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, hotelGuestListDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
