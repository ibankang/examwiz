package examwiz.ibankang.com.SubAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import examwiz.ibankang.com.MainActivity;
import examwiz.ibankang.com.R;

public class subadmin_year_exam_activity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subadmin_year_exam_activity);


        ((ImageView)findViewById(R.id.add_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(subadmin_year_exam_activity.this, subadmin_create_exam_activity.class));

            }
        });
    }
}