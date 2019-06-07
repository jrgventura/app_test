package com.ventura.apptest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
@RunWith(MockitoJUnitRunner.class)
public class SigninPresenterTest {

    int totalNumberOfApplicants = 0;

    @Before
    public void setData(){
        this.totalNumberOfApplicants = 9;
    }

    @Test
    public void testAssertThatEqual() {
        assertThat("123",is("123"));
    }

    @Test
    public void testAssertThatNotEqual() {
        assertThat(totalNumberOfApplicants,is(123));
    }

    @Test
    public void testAssertThatObject() {
        assertThat("123",isA(String.class));
    }

    @Test
    public void testAssertThatWMessage(){
        assertThat("They are not equal!","123",is("1234"));
    }

}
