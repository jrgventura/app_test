package com.ventura.apptest.presentation.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ventura.apptest.R;
import com.ventura.apptest.presentation.home.list_place.PlaceHomeActivity;
import com.ventura.apptest.presentation.register.RegisterActivity;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class SigninActivity extends AppCompatActivity implements SigninInterface.View, View.OnClickListener {

    private EditText edtemail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    private SigninInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        presenter = new SigninPresenter(this);

        edtemail = (EditText) findViewById(R.id.edtemail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Login");
        loadingBar.setMessage("Please wait ...");
        loadingBar.setCanceledOnTouchOutside(true);

        setEvent();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            onNavigateHome();
        }
    }

    private void setEvent(){
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    public String validate(String userName, String password)
    {
        if(userName.equals("user") && password.equals("user"))
            return "Login was successful";
        else
            return "Invalid login!";
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnLogin:
                setLogin(edtemail.getText().toString().trim(), edtPassword.getText().toString().trim());
                break;

            case R.id.btnRegister:
                onNavigationRegister();
                break;

            default:
                break;

        }
    }

    public void setLogin(String email, String password){
        if(presenter.isValidForm(email, password)) {
            presenter.attemptSignin(email, password);
        }
    }


    @Override
    public void onNavigateHome() {
        Intent mainIntent = new Intent(this, PlaceHomeActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    @Override
    public void onNavigationRegister() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        registerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(registerIntent);
    }


    @Override
    public void displayEmailError(String error) {
        edtemail.setError(error);
    }

    @Override
    public void displayPasswordError(String error) {
        edtPassword.setError(error);
    }

    @Override
    public void displaySigninError(String error) {
        showMessage(error);
    }

    @Override
    public void displayLoader(boolean loader) {
        if (loader) {
            loadingBar.show();
        } else {
            loadingBar.dismiss();
        }
    }

    @Override
    public void setEnabledView(boolean enable) {
        edtemail.setEnabled(enable);
        edtPassword.setEnabled(enable);
        btnLogin.setEnabled(enable);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
