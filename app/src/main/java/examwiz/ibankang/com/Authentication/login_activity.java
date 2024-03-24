package examwiz.ibankang.com.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import examwiz.ibankang.com.R;

public class login_activity extends AppCompatActivity {

    TextView signup_txt, forgot_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        signup_txt = findViewById(R.id.signup_txt);
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,signup_activity.class));
            }
        });

        forgot_txt = findViewById(R.id.forgot_txt);
        forgot_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,forgot_password_activity.class));
            }
        });
    }
}