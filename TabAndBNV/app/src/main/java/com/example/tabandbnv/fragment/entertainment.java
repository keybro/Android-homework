package com.example.tabandbnv.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tabandbnv.MainActivity;
import com.example.tabandbnv.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link entertainment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class entertainment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public entertainment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment entertainment.
     */
    // TODO: Rename and change types and number of parameters
    public static entertainment newInstance(String param1, String param2) {
        entertainment fragment = new entertainment();
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
        View inflate = inflater.inflate(R.layout.fragment_entertainment, container, false);

//        //使用Glid获取在线图片
        ImageView imageView = (ImageView)inflate.findViewById(R.id.imageView);
//        //图片连接
        String url = "https://s3.bmp.ovh/imgs/2021/12/5cf4d5959609509a.png";
//
        Glide.with(this).load(url).into(imageView);
        return inflate;
    }
}