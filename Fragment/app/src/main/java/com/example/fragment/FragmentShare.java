package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentShare#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentShare extends Fragment {
    /**
     * 用户选择的新闻
     */
    String userChoice = "";

    /**
     * 接口定义，用户向activity传递参数
     */
    public interface Mylistener{
        public void thanks(String code);
    }
    private Mylistener listener;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentShare() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentShare.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentShare newInstance(String param1) {
        FragmentShare fragment = new FragmentShare();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_share, container, false);

        if (getArguments()!=null){
            userChoice = getArguments().getString("param");
            TextView textView = inflate.findViewById(R.id.txt_content);
            textView.setText(userChoice);
        }

        Button button = inflate.findViewById(R.id.share);
        class share implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                TextView textView = mainActivity.findViewById(R.id.listHint);
                textView.setText("即将分享："+userChoice);
            }
        }
        button.setOnClickListener(new share());

        return inflate;
    }


}