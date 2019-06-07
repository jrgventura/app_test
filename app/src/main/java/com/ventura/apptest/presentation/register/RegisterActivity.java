package com.ventura.apptest.presentation.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ventura.apptest.R;
import com.ventura.apptest.presentation.home.list_place.PlaceHomeActivity;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterInterface.View, View.OnClickListener{

    private EditText edtemail;
    private EditText edtPassword;
    private EditText edtConfirmationPassword;
    private Button btnRegister;

    private ProgressDialog loadingBar;
    private RegisterInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);

        edtemail = (EditText) findViewById(R.id.edtemail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmationPassword = (EditText) findViewById(R.id.edtConfirmationPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Creating New Account");
        loadingBar.setMessage("Please wait, while we are creating your new Account...");
        loadingBar.setCanceledOnTouchOutside(true);

        setEvent();
    }


    private void setEvent(){
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRegister:
                setRegister();
                break;

            default:
                break;
        }

    }

    private void setRegister(){
        final String email = edtemail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        final String conf_password = edtConfirmationPassword.getText().toString().trim();
        if(presenter.isValidForm(email, password, conf_password)) {
            presenter.attemptRegister(email, password);
        }
    }


    @Override
    public void onNavigateHome() {
        Intent setupIntent = new Intent(RegisterActivity.this, PlaceHomeActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
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
    public void displayRegisterError(String error) {
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
        btnRegister.setEnabled(enable);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
