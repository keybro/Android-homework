package com.example.tabandbnv.data;

import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditViewModel extends ViewModel {

    private MutableLiveData<String> EditContent;

    public MutableLiveData<String> getEditContent() {
        if (EditContent == null){
            EditContent = new MutableLiveData<String>();
        }
        return EditContent;
    }
}
