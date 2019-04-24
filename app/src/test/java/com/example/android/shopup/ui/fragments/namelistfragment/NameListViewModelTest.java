package com.example.android.shopup.ui.fragments.namelistfragment;

import android.app.Application;
import android.view.View;

import androidx.databinding.ObservableField;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class NameListViewModelTest {

    private Application application;
    private NameListViewModel subject;
    private View view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = Mockito.spy(new NameListViewModel(application));
    }

    @Test
    public void onObjectClicked(){
        subject.openListFragment(view);
    }
}