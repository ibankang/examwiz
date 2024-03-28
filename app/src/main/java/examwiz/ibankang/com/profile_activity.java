package examwiz.ibankang.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class profile_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        get_profile_data();

    }

    void get_profile_data(){

        // Get Firebase user
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            // Get user ID
            String userId = firebaseUser.getUid();

            // Get Firestore instance
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Get document reference
            DocumentReference docRef = db.collection("account").document(userId);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Retrieve user data
                            String username = document.getString("name");
                            String email = document.getString("email");
                            String uni_name = document.getString("university");
                            String account_type = document.getString("account_type");
                            String dept = document.getString("department");
                            String rollno = document.getString("roll_no");
                            String regno = document.getString("reg_no");
                            String mobile = document.getString("phone");
                            String passout = document.getString("passout_year");

                            // Set user data in drawer header
                            TextView usernameTextView = findViewById(R.id.username_txt);
                            TextView emailTextView = findViewById(R.id.user_email);
                            TextView uni_name_txt = findViewById(R.id.uni_name_txt);
                            TextView account_type_txt = findViewById(R.id.account_type);
                            TextView dept_txt = findViewById(R.id.dept_name_txt);
                            TextView rollno_txt = findViewById(R.id.roll_txt);
                            TextView regno_txt = findViewById(R.id.reg_txt);
                            TextView mobile_txt = findViewById(R.id.user_phone);
                            TextView passout_txt = findViewById(R.id.passout_txt);

                            usernameTextView.setText(username);
                            emailTextView.setText(email);
                            uni_name_txt.setText(uni_name);
                            account_type_txt.setText(account_type);
                            dept_txt.setText(dept);
                            rollno_txt.setText(rollno);
                            regno_txt.setText(regno);
                            mobile_txt.setText(mobile);
                            passout_txt.setText(passout);

                        }
                    } else {
                        Log.d("MainActivity", "Document retrieval failed:", task.getException());
                    }
                }
            });
        }
    }
}