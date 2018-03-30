package com.example.leon.myapp.Views.Registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon.myapp.App;
import com.example.leon.myapp.Enumerations.ValidationErrorEnum;
import com.example.leon.myapp.Presenter.Registration.RegistrationActivityPresenter;
import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Login.LoginActivityView;

import javax.inject.Inject;

public class RegistrationFragmentView extends Fragment {
    private EditText editTextFirstName, editTextSecondName, editTextAge, editTextEmailView, editTextPasswordView;
    private Button buttonRegister;
    private String registeredEmail;

    @Inject
    protected RegistrationActivityPresenter presenter;

    public RegistrationFragmentView() {
        // Required empty public constructor
    }

    public static RegistrationFragmentView newInstance() {
        RegistrationFragmentView fragment = new RegistrationFragmentView();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getApp().getDataComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //get views
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        editTextFirstName    = view.findViewById(R.id.first_name);
        editTextSecondName   = view.findViewById(R.id.second_name);
        editTextAge          = view.findViewById(R.id.age);
        editTextEmailView    = view.findViewById(R.id.email);
        editTextPasswordView = view.findViewById(R.id.password);
        buttonRegister       = view.findViewById(R.id.register_button);

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
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegistrationAction();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
            Intent intentLogin = new Intent(getActivity(), LoginActivityView.class);
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
            toast = Toast.makeText(getActivity(), getString(R.string.registration_successful), Toast.LENGTH_SHORT);
        }
        else {
            toast = Toast.makeText(getActivity(), getString(R.string.registration_failed), Toast.LENGTH_SHORT);
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
