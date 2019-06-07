package com.ventura.apptest.presentation.register;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public interface RegisterInterface {

    interface View {
        void onNavigateHome();
        void displayEmailError(String error);
        void displayPasswordError(String error);
        void displayRegisterError(String error);
        void displayLoader(boolean loader);
        void setEnabledView(boolean enable);
    }

    interface Presenter {
        void attemptRegister(String email, String password);
        boolean isValidForm(String email, String password, String conf_password);
        void onDestroy();
    }

    interface Interactor {
        void performRegister(String email, String password);
    }

    interface CompleteListener {
        void onSuccess();
        void onError();
    }

}
