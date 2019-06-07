package com.ventura.apptest.presentation.register;

import android.util.Patterns;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class RegisterPresenter implements RegisterInterface.Presenter, RegisterInterface.CompleteListener {

    private RegisterInterface.View view;
    private RegisterInterface.Interactor interactor;

    public RegisterPresenter(RegisterInterface.View view) {
        this.view = view;
        this.interactor = new RegisterInteractor(this);
    }

    @Override
    public void attemptRegister(String email, String password) {
        if(view != null) {
            view.setEnabledView(false);
            view.displayLoader(true);
        }
        interactor.performRegister(email, password);
    }

    @Override
    public boolean isValidForm(String email, String password, String conf_password) {
        boolean isValid = true;
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false;
            view.displayEmailError("Format email incorrect");
        } else if(password.length() <= 4) {
            isValid = false;
            view.displayPasswordError("Format password incorrect");
        } else if(!password.equals(conf_password)){
            isValid = false;
            view.displayPasswordError("Confirmation password incorrect");
        }
        return isValid;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.onNavigateHome();
        }
    }

    @Override
    public void onError() {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.displayRegisterError("Error register");
        }
    }
}
