package com.example.leon.myapp.Views.Login;

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
import com.example.leon.myapp.Presenter.Login.LoginActivityPresenter;
import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Main.MainActivity;
import com.example.leon.myapp.Views.Registration.RegistrationActivity;

import javax.inject.Inject;

public class LoginFragment extends Fragment {
    private EditText editTextEmailView, editTextPasswordView;
    private Button buttonLogin, buttonRegister;

    @Inject
    protected LoginActivityPresenter presenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmailView    = view.findViewById(R.id.login_email);
        editTextPasswordView = view.findViewById(R.id.login_password);
        buttonLogin          = view.findViewById(R.id.login_button);
        buttonRegister       = view.findViewById(R.id.login_register_button);

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
                startActivity(new Intent(getActivity(), RegistrationActivity.class));
                resetFields();
            }
        });

        //check if an email was just registered
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null) {
            editTextEmailView.setText(extras.getString(getString(R.string.registration_email)));
            editTextPasswordView.requestFocus();
        }


        //show start animation
        //AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.test_button_animator);
        //set.setTarget(buttonLogin);
        //set.start();

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
        editTextPasswordView.setText("");
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
     * method called when login is triggered
     */
    private void handleLoginAction() {
        //reset possible errors
        editTextEmailView.setError(null);
        editTextPasswordView.setError(null);

        boolean loginSuccessful = attemptLogin();

        showToast(loginSuccessful);
        if (loginSuccessful) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
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
                this.showToast(false);
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
            toast = Toast.makeText(getActivity(), getString(R.string.login_successful), Toast.LENGTH_SHORT);
        }
        else {
            toast = Toast.makeText(getActivity(), getString(R.string.login_failed), Toast.LENGTH_SHORT);
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
