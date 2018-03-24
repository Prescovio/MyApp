package com.example.leon.myapp.Views.Registration;

import com.example.leon.myapp.*;
import com.example.leon.myapp.Enumerations.ValidationErrorEnum;
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

import javax.inject.Inject;

public class RegistrationActivityView extends AppCompatActivity {
    private Intent intentLogin;

    private EditText editTextFirstName, editTextSecondName, editTextAge, editTextEmailView, editTextPasswordView;
    private Button buttonRegister;
    private String registeredEmail;

    @Inject
    protected RegistrationActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        App.getApp().getDataComponent().inject(this);

        //intent to return to login
        intentLogin = new Intent(this, LoginActivityView.class);

        //presenter
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

    /**
     * method called when registration is triggered
     */
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

    /**
     * check fields for correctness, calls register()
     * @return registrationSuccessful
     */
    private boolean attemptRegistration() {
        //get text values
        String firstName = editTextFirstName.getText().toString();
        String secondName = editTextSecondName.getText().toString();
        String age = editTextAge.getText().toString();
        String email = editTextEmailView.getText().toString();
        String password = editTextPasswordView.getText().toString();

        //indicates whether at least one field is invalid
        boolean cancel = false;
        //saves the last invalid field
        View focusView = null;

        //first name
        ValidationErrorEnum error = presenter.validateFirstName(firstName);
        if (error == ValidationErrorEnum.Empty) {
            editTextFirstName.setError(getString(R.string.error_field_required));
            focusView = editTextFirstName;
            cancel = true;
        } else if (error == ValidationErrorEnum.Invalid) {
            editTextFirstName.setError(getString(R.string.error_invalid_first_name));
            focusView = editTextFirstName;
            cancel = true;
        }

        //second name
        error = presenter.validateSecondName(secondName);
        if (error == ValidationErrorEnum.Empty) {
            editTextSecondName.setError(getString(R.string.error_field_required));
            focusView = editTextSecondName;
            cancel = true;
        } else if (error == ValidationErrorEnum.Invalid) {
            editTextSecondName.setError(getString(R.string.error_invalid_second_name));
            focusView = editTextSecondName;
            cancel = true;
        }

        //age
        error = presenter.validateAge(age);
        if (error == ValidationErrorEnum.Empty) {
            editTextAge.setError(getString(R.string.error_field_required));
            focusView = editTextAge;
            cancel = true;
        } else if (error == ValidationErrorEnum.Invalid) {
            editTextAge.setError(getString(R.string.error_invalid_age));
            focusView = editTextAge;
            cancel = true;
        }

        //email
        error = presenter.validateEmail(email);
        if (error == ValidationErrorEnum.Empty) {
            editTextEmailView.setError(getString(R.string.error_field_required));
            focusView = editTextEmailView;
            cancel = true;
        } else if (error == ValidationErrorEnum.Invalid) {
            editTextEmailView.setError(getString(R.string.error_invalid_email));
            focusView = editTextEmailView;
            cancel = true;
        }

        //password
        error = presenter.validatePassword(password);
        if (error == ValidationErrorEnum.Empty) {
            editTextPasswordView.setError(getString(R.string.error_field_required));
            focusView = editTextPasswordView;
            cancel = true;
        } else if (error == ValidationErrorEnum.Invalid) {
            editTextPasswordView.setError(getString(R.string.error_invalid_password));
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

    /**
     * give feedback if registration worked
     * @param success
     */
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

    /**
     * reset field content
     */
    private void resetFields() {
        editTextFirstName.requestFocus();
        editTextFirstName.setText("");
        editTextSecondName.setText("");
        editTextAge.setText("");
        editTextEmailView.setText("");
        editTextPasswordView.setText("");
    }
}

