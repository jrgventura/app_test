package com.ventura.apptest;

import android.content.Context;

import com.ventura.apptest.presentation.login.SigninActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginUnitTest {

    private static final String FAKE_STRING = "Login was successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {

        SigninActivity myObjectUnderTest = new SigninActivity(mMockContext);
        String result = myObjectUnderTest.validate("user","user");
        assertThat(result, is(FAKE_STRING));

    }

}
