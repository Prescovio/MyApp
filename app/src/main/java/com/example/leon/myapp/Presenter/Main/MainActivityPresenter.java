package com.example.leon.myapp.Presenter.Main;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.view.View;

import com.example.leon.myapp.BR;

public class MainActivityPresenter implements Observable {
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();
    private String text = "Test";

    public MainActivityPresenter() {

    }

    public void handleEvent(View view) {
        this.setText("Hallo :)");
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        registry.notifyChange(this, BR.text);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);
    }
}
