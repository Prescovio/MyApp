package com.example.leon.myapp.Views.Registration;
import com.example.leon.myapp.*;
import com.example.leon.myapp.Models.Database.UserDbHelper;
import com.example.leon.myapp.Presenter.Registration.RegistrationActivityPresenter;
import com.example.leon.myapp.Views.Login.LoginActivityView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivityView extends AppCompatActivity {
    private Intent intentLogin;

    private RegistrationActivityPresenter presenter;

    private EditText editTextFirstName, editTextSecondName, editTextAge, editTextEmailView, editTextPasswordView;
    private Button buttonRegister;
    private UserDbHelper dbHelper;
    private String registeredEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //intent to return to login
        intentLogin = new Intent(this, LoginActivityView.class);

        //presenter
        presenter = new RegistrationActivityPresenter(getBaseContext());

        //get views
        editTextFirstName = (EditText)findViewById(R.id.first_name);
        editTextSecondName = (EditText)findViewById(R.id.second_name);
        editTextAge = (EditText)findViewById(R.id.age);
        editTextEmailView = (EditText)findViewById(R.id.email);
        editTextPasswordView = (EditText)findViewById(R.id.password);
        buttonRegister = (Button)findViewById(R.id.register_button);

        //set listener on last editbox
        editTextPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    handleRegistrationAction();

                    return true;
                }
                return false;
            }
        });

        //set listener on button
        buttonRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegistrationAction();
            }
        });
    }

    //method called when registration is triggered
    private void handleRegistrationAction() {
        //reset possible errors
        editTextFirstName.setError(null);
        editTextSecondName.setError(null);
        editTextAge.setError(null);
        editTextEmailView.setError(null);
        editTextPasswordView.setError(null);

        boolean registrationSuccessful = attemptRegistration();

        showToast(registrationSuccessful);
        if (registrationSuccessful) {
            intentLogin.putExtra(getString(R.string.registration_email), registeredEmail);
            startActivity(intentLogin);
            resetFields();
        }
    }

    //check fields for correctness, calls register()
    private boolean attemptRegistration() {
        //get text values
        String firstName = editTextFirstName.getText().toString();
        String secondName = editTextSecondName.getText().toString();
        String age = editTextAge.getText().toString();
        String email = editTextEmailView.getText().toString();
        String password = editTextPasswordView.getText().toString();

        //indicates if at least one field is invalid
        boolean cancel = false;
        //saves the last invalid field
        View focusView = null;

        String error = presenter.validateFirstName(firstName);
        if (!error.isEmpty()) {
            editTextFirstName.setError(error);
            focusView = editTextFirstName;
            cancel = true;
        }

        error = presenter.validateFirstName(secondName);
        if (!error.isEmpty()) {
            editTextSecondName.setError(error);
            focusView = editTextSecondName;
            cancel = true;
        }

        error = presenter.validateAge(age);
        if (!error.isEmpty()) {
            editTextAge.setError(error);
            focusView = editTextAge;
            cancel = true;
        }

        error = presenter.validateEmail(email);
        if (!error.isEmpty()) {
            editTextEmailView.setError(error);
            focusView = editTextEmailView;
            cancel = true;
        }

        error = presenter.validatePassword(password);
        if (!error.isEmpty()) {
            editTextPasswordView.setError(error);
            focusView = editTextPasswordView;
            cancel = true;
        }

        //fields are okay, but user not available
        if (!cancel && !presenter.userAvailable(email)) {
            editTextEmailView.setError(getString(R.string.error_email_registered));
            focusView = editTextEmailView;
            cancel = true;
        }

        //at least one field is not okay
        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            boolean registrationSuccessful = presenter.register(firstName, secondName, Integer.parseInt(age), email, password);
            registeredEmail = email;

            return registrationSuccessful;
        }
    }

    //give feedback if registration worked
    private void showToast(boolean success) {
        Toast toast;
        if (success) {
            toast = Toast.makeText(getBaseContext(), getString(R.string.registration_successful), Toast.LENGTH_SHORT);
        }
        else {
            toast = Toast.makeText(getBaseContext(), getString(R.string.registration_failed), Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    private void resetFields() {
        editTextFirstName.requestFocus();
        editTextFirstName.setText("");
        editTextSecondName.setText("");
        editTextAge.setText("");
        editTextEmailView.setText("");
        editTextPasswordView.setText("");
    }
}

