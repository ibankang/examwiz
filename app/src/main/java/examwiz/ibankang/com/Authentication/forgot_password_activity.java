package examwiz.ibankang.com.Authentication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import examwiz.ibankang.com.R;

public class forgot_password_activity extends AppCompatActivity {

    TextView login_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        // Initialize views
        login_txt = findViewById(R.id.login_txt);

        // Set click listener for login text to navigate to login activity
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(forgot_password_activity.this, login_activity.class));
            }
        });

        // Set click listener for forgot password button
        ((Button)findViewById(R.id.forgot_password_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpasswordInFirebase();
            }
        });
    }

    // Method to handle forgot password functionality
    void forgotpasswordInFirebase() {
        TextInputLayout emile = (TextInputLayout) findViewById(R.id.email_edit_txt);

        // Check if email entered is valid
        if (emile.getEditText().getText().length() > 5) {
            Toast.makeText(this, "Sending..", Toast.LENGTH_SHORT).show();

            // Send password reset email using FirebaseAuth
            FirebaseAuth.getInstance().sendPasswordResetEmail(emile.getEditText().getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // If email sent successfully, show a toast and navigate back to login activity
                                Toast.makeText(forgot_password_activity.this, "Send Successful", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Email sent.");
                                startActivity(new Intent(forgot_password_activity.this, login_activity.class));
                                finish();
                            }
                        }
                    });
        } else {
            // If email is not entered or invalid, show a toast
            Toast.makeText(this, "Enter email id ?", Toast.LENGTH_SHORT).show();
        }
    }
}
