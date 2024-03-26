package examwiz.ibankang.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import examwiz.ibankang.com.adminUi.AdminCalendarFragment;
import examwiz.ibankang.com.adminUi.AdminHomeFragment;
import examwiz.ibankang.com.adminUi.AdminSearchFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView menu_btn = findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else {
                    drawerLayout.openDrawer(GravityCompat.START);
                    NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
                    navigationView.setNavigationItemSelectedListener(item -> {

                        int id = item.getItemId();
                        if (id == R.id.nav_home){
                            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (id == R.id.nav_settings){
                            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (id == R.id.nav_share) {
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                            sendIntent.setType("text/plain");

                            Intent shareIntent = Intent.createChooser(sendIntent, null);
                            startActivity(shareIntent);
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (id == R.id.nav_about) {
                            Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (id == R.id.nav_logout){
                            Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        return false;
                    });
                }

            }
        });

        replaceFragment(new AdminHomeFragment());


        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new AdminHomeFragment());
            } else if (item.getItemId() == R.id.search) {
                replaceFragment(new AdminSearchFragment());
            } else if (item.getItemId() == R.id.calendar) {
                replaceFragment(new AdminCalendarFragment());
            }
            return true;
        });
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}