package com.ventura.apptest.presentation.login;

import android.util.Patterns;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class SigninPresenter implements SigninInterface.Presenter, SigninInterface.CompleteListener{

    private SigninInterface.View view;
    private SigninInterface.Interactor interactor;

    public SigninPresenter(SigninInterface.View view) {
        this.view = view;
        this.interactor = new SigninInteractor(this);
    }

    @Override
    public void attemptSignin(String email, String password) {
        if(view != null) {
            view.setEnabledView(false);
            view.displayLoader(true);
        }
        interactor.performSignin(email, password);
    }

    @Override
    public boolean isValidForm(String email, String password) {
        boolean isValid = true;
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false;
            view.displayEmailError("Format email incorrect");
        }
        else if(password.length() <= 4) {
            isValid = false;
            view.displayPasswordError("Format password incorrect");
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
            view.displaySigninError("Error authentication");
        }
    }

}
