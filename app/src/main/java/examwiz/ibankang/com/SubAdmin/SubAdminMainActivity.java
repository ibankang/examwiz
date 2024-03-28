package examwiz.ibankang.com.SubAdmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import examwiz.ibankang.com.Authentication.login_activity;
import examwiz.ibankang.com.Authentication.splash_screen_activity;
import examwiz.ibankang.com.MainActivity;
import examwiz.ibankang.com.R;
import examwiz.ibankang.com.adminUi.AdminCalendarFragment;
import examwiz.ibankang.com.adminUi.AdminHomeFragment;
import examwiz.ibankang.com.adminUi.AdminSearchFragment;
import examwiz.ibankang.com.profile_activity;

public class SubAdminMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    SharedPreferences sharedPreferences;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_admin_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        recyclerView=  findViewById(R.id.subAdminRv);

//        List<ExamItemClass> items = new ArrayList<ExamItemClass>();
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));
//
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));
//
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));
//
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));
//
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));
//
//        items.add(new ExamItemClass("10-4-2024","Monday","MST 3rd year","Java Programming","10:00 AM - 11:00 AM"));


//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new SubAdminAdapter(getApplicationContext(),items) );

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFragment(new AdminHomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    replaceFragment(new AdminHomeFragment());
                } else if (item.getItemId() == R.id.search) {
                    replaceFragment(new AdminSearchFragment());
                } else if (item.getItemId() == R.id.calander) {
                    replaceFragment(new AdminCalendarFragment());
                }
                return true;
            }
        });


        drawerLayout = findViewById(R.id.main);
        ImageView menu_btn = (ImageView) findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    //
                    setUserInfoInDrawer();
                    drawerLayout.openDrawer(GravityCompat.START);
                    NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
                    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                            int id = item.getItemId();
                            if (id == R.id.nav_home) {
                                startActivity(new Intent(SubAdminMainActivity.this, splash_screen_activity.class));
                                drawerLayout.closeDrawer(GravityCompat.START);
                            }
//                            else if (id == R.id.nav_admin) {
//                                startActivity(new Intent(MainActivity.this, splash_screen_activity.class));
//                                drawerLayout.closeDrawer(GravityCompat.START);
//                            }
                            else if (id == R.id.nav_logout) {
                                logoutUser();
                                drawerLayout.closeDrawer(GravityCompat.START);
                            }
                            return false;
                        }
                    });
                }

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void logoutUser() {
        // Log out the user using Firebase Authentication
        FirebaseAuth.getInstance().signOut();

        // Open the login activity
        openLoginActivity();
    }

    // Method to open the login activity
    private void openLoginActivity() {
        Log.d("MainActivity", "Logging out and opening LoginActivity");
        Intent intent = new Intent(this, login_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // finish();
    }

    private void setUserInfoInDrawer() {
        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        ((ImageView)findViewById(R.id.user_photo_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubAdminMainActivity.this, profile_activity.class));
            }
        });

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

                            // Set user data in drawer header
                            TextView usernameTextView = headerView.findViewById(R.id.user_name_txt);
                            TextView emailTextView = headerView.findViewById(R.id.user_email_id_txt);
                            usernameTextView.setText(username);
                            emailTextView.setText(email);

                            // Open drawer
                            drawerLayout.openDrawer(GravityCompat.START);
                        }
                    } else {
                        Log.d("MainActivity", "Document retrieval failed:", task.getException());
                    }
                }
            });
        }
    }
}