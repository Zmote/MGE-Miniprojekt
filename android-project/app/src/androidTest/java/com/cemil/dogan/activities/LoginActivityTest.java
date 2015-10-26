package com.cemil.dogan.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zmote on 26.10.2015.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    Button loginBtn;
    EditText email;
    EditText password;
    LoginActivity loginActivity;
    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp(){
        loginActivity = getActivity();
        loginBtn = (Button) loginActivity.findViewById(R.id.btn_login);
        email = (EditText) loginActivity.findViewById(R.id.input_email);
        password = (EditText) loginActivity.findViewById(R.id.input_password);
    }

    public void testLoginOpensAdminActivity(){
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(AdminActivity.class.getName(),
                        null, false);
        email.setText("zmote@hsr.ch");
        password.setText("zmote");
        TouchUtils.clickView(this, loginBtn);
        AdminActivity receiverActivity = (AdminActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(3000);
        assertNull("ReceiverActivity is null", receiverActivity);
    }
}
