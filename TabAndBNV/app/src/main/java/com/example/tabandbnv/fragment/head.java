package com.example.tabandbnv.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tabandbnv.R;
import com.example.tabandbnv.data.EditViewModel;
import com.example.tabandbnv.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link head#newInstance} factory method to
 * create an instance of this fragment.
 */
public class head extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public head() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment head.
     */
    // TODO: Rename and change types and number of parameters
    public static head newInstance(String param1, String param2) {
        head fragment = new head();
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
        View inflate = inflater.inflate(R.layout.fragment_head, container, false);

        //???fragment??????listview?????????
        ListView listView = inflate.findViewById(R.id.headListView);
        String str[] = {"??????????????????????????????","???????????????????????????","?????????????????????AK47"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_list_item_1,str);
        listView.setAdapter(adapter);

        //???listview???item??????????????????
        class ItemClick implements AdapterView.OnItemClickListener{
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(getActivity().getApplicationContext(),str[position], Toast.LENGTH_LONG);
            }
        }
        listView.setOnItemClickListener(new ItemClick());

        //?????????????????????livedata,??????????????????viewModel
        EditViewModel editViewModel = ViewModelProviders.of(getActivity()).get(EditViewModel.class);
        MutableLiveData<String> editContent = editViewModel.getEditContent();
        editContent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //System.out.println("?????????????????????");
            }
        });

        EditText editText = inflate.findViewById(R.id.headInput);

        Button button = inflate.findViewById(R.id.headButton);
        //??????livedata??????
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editViewModel.getEditContent().setValue(editText.getText().toString());
                //System.out.println(editViewModel.getEditContent());
                //System.out.println("livedata???????????????");
            }
        });

        return inflate;

    }
}