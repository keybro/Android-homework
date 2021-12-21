package com.example.tabandbnv.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tabandbnv.R;
import com.example.tabandbnv.data.EditViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sports#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sports extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sports() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sports.
     */
    // TODO: Rename and change types and number of parameters
    public static sports newInstance(String param1, String param2) {
        sports fragment = new sports();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_sports, container, false);

        TextView textView = inflate.findViewById(R.id.sportsTextView);

        //为了获取livedata响应，先获取viewModel
        EditViewModel editViewModel = ViewModelProviders.of(getActivity()).get(EditViewModel.class);

        MutableLiveData<String> editContent = editViewModel.getEditContent();
        editContent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
                //System.out.println("sports接收到变化");
            }
        });

        return inflate;


    }
}