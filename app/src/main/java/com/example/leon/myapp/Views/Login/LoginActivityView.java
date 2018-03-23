package com.example.leon.myapp.Views.Login;
import com.example.leon.myapp.*;
import com.example.leon.myapp.Enumerations.ValidationErrorEnum;
import com.example.leon.myapp.Presenter.Login.LoginActivityPresenter;
import com.example.leon.myapp.Views.Main.MainActivity;
import com.example.leon.myapp.Views.Registration.RegistrationActivityView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivityView extends AppCompatActivity {
    private Intent intentRegister, intentMain;

    private EditText editTextEmailView, editTextPasswordView;
    private Button buttonLogin, buttonRegister;

    private LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //intent to switch to registration or main
        intentRegister = new Intent(this, RegistrationActivityView.class);
        intentMain = new Intent(this, MainActivity.class);

        presenter = new LoginActivityPresenter(getBaseContext());

        //get views
        editTextEmailView = (EditText)findViewById(R.id.login_email);
        editTextPasswordView = (EditText)findViewById(R.id.login_password);
        buttonLogin = (Button)findViewById(R.id.login_button);
        buttonRegister = (Button)findViewById(R.id.login_register_button);

        //set listener on last editbox
        editTextPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    handleLoginAction();
                    return true;
                }
                return false;
            }
        });

        //set listener on login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleLoginAction();
            }
        });

        //set listener on register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intentRegister);
                resetFields();
            }
        });

        //check if an email was just registered
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            editTextEmailView.setText(extras.getString(getString(R.string.registration_email)));
            editTextPasswordView.requestFocus();
        }
    }

    /**
     * method called when login is triggered
     */
    private void handleLoginAction() {
        //reset possible errors
        editTextEmailView.setError(null);
        editTextPasswordView.setError(null);

        boolean loginSuccessful = attemptLogin();

        showToast(loginSuccessful);
        if (loginSuccessful) {
            startActivity(intentMain);
            resetFields();
        }
    }

    /**
     * validate fields and call login
     * @return loginSuccessful
     */
    private boolean attemptLogin() {
        //indicates whether at least one field is invalid
        boolean cancel = false;
        //saves the last invalid field
        View focusView = null;

        //get text values
        String email = editTextEmailView.getText().toString();
        String password = editTextPasswordView.getText().toString();


        //email
        ValidationErrorEnum error = presenter.validateEmail(email);
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

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            boolean loginSuccessful = presenter.login(email, password);

            if (!loginSuccessful) {
                editTextPasswordView.setText("");
            }

            return loginSuccessful;
        }
    }

    /**
     * give feedback whether registration worked
     * @param success
     */
    private void showToast(boolean success) {
        Toast toast;
        if (success) {
            toast = Toast.makeText(getBaseContext(), getString(R.string.login_successful), Toast.LENGTH_SHORT);
        }
        else {
            toast = Toast.makeText(getBaseContext(), getString(R.string.login_failed), Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    /**
     * reset field content
     */
    private void resetFields() {
        editTextEmailView.requestFocus();
        editTextEmailView.setText("");
        editTextPasswordView.setText("");
    }
}
