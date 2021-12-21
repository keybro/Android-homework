package com.example.tabandbnv.ui.myNavagation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tabandbnv.R;

public class myFragment extends Fragment {

    private MyViewModel mViewModel;

    public static myFragment newInstance() {
        return new myFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate =  inflater.inflate(R.layout.my_fragment, container, false);

        Button toCreateBtn = inflate.findViewById(R.id.createBtn);
        toCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_my_to_createFragment);
            }
        });
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // TODO: Use the ViewModel
    }

}