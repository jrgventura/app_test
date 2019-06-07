package com.ventura.apptest.presentation.login;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public interface SigninInterface {

    interface View {
        void onNavigateHome();
        void onNavigationRegister();

        void displayEmailError(String error);
        void displayPasswordError(String error);
        void displaySigninError(String error);

        void displayLoader(boolean loader);
        void setEnabledView(boolean enable);
    }

    interface Presenter {
        void attemptSignin(String email, String password);
        boolean isValidForm(String email, String password);
        void onDestroy();
    }

    interface Interactor {
        void performSignin(String email, String password);
    }

    interface CompleteListener {
        void onSuccess();
        void onError();
    }

}
