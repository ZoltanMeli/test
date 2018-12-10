package com.ml.zszabo.segunda;

import android.view.View;
import android.widget.EditText;

import com.ml.zszabo.segunda.Presenter.LoginPresenter;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginUnitTest {

    @Test
    public void testEmail() {
        LoginPresenter presenter = new LoginPresenter();
        presenter.setEmail("asdf");
        assertFalse(presenter.isEmailValid());
        presenter.setEmail("asd@asd.com");
        assertTrue(presenter.isEmailValid());
    }

    @Test
    public void testPassword() {
        LoginPresenter presenter = new LoginPresenter();
        presenter.setPassword("asd");
        assertFalse(presenter.isPasswordValid());
        presenter.setPassword("asdef");
        assertTrue(presenter.isPasswordValid());
    }


}