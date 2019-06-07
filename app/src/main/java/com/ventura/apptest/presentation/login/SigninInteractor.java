package com.ventura.apptest.presentation.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class SigninInteractor implements SigninInterface.Interactor {

    private SigninInterface.CompleteListener listener;
    private FirebaseAuth auth;

    public SigninInteractor(SigninInterface.CompleteListener listener) {
        this.listener = listener;
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void performSignin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            listener.onSuccess();
                        else
                            listener.onError();
                    }

                });
    }
}
