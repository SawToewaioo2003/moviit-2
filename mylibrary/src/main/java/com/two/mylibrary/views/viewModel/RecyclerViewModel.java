package com.two.mylibrary.views.viewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.two.mylibrary.views.baseClasses.globalEnums.ListStatus;

public class RecyclerViewModel extends ViewModel {
    private MutableLiveData<ListStatus> state = new MutableLiveData<>();

    public MutableLiveData<ListStatus> getState() {
        return state;
    }
}
