package examwiz.ibankang.com.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import examwiz.ibankang.com.R;

public class forgot_password_activity extends AppCompatActivity {

    TextView login_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        login_txt = findViewById(R.id.login_txt);
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(forgot_password_activity.this,login_activity.class));
            }
        });
    }
}