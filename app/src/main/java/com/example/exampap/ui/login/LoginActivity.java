package com.example.exampap.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exampap.HomePage;
import com.example.exampap.R;
import com.example.exampap.RetrofitClientInterface;
import com.example.exampap.UserDataService;
import com.example.exampap.data.model.LoggedInUser;
import com.example.exampap.ui.login.LoginViewModel;
import com.example.exampap.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View backgroundImage = findViewById(R.id.container);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(80);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        final EditText studentNumberEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        //TODO Remove this 3 when demoing the app after dine
        studentNumberEditText.setText("094567");
        passwordEditText.setText("password");
        loginButton.setEnabled(true);


//        loginButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                loadingProgressBar.setVisibility(View.VISIBLE);
//                String studentNumber = studentNumberEditText.getText().toString().trim();
//                String password = passwordEditText.getText().toString().trim();
//                LoggedInUser loggedInUser = new LoggedInUser(studentNumber, password);
//                loginUser(loggedInUser);
//                Intent intent1= new Intent(LoginActivity.this, HomePage.class);
//                startActivity(intent1);
//                loadingProgressBar.setVisibility(View.GONE);
//
//            }
//        });
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    studentNumberEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(studentNumberEditText.getText().toString(),
                        passwordEditText.getText().toString());
                Intent intent1= new Intent(LoginActivity.this, HomePage.class);
                startActivity(intent1);
            }

        });
    }

    private static void loginUser(LoggedInUser loggedInUser)
    {
        UserDataService userDataService = RetrofitClientInterface.getRetrofitInstance().create(UserDataService.class);
        userDataService.loginUser(loggedInUser);
    }



    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}