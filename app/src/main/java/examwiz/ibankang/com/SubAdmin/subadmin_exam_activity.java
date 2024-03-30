package examwiz.ibankang.com.SubAdmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import examwiz.ibankang.com.R;

public class subadmin_exam_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subadmin_exam_activity);
        CardView yr_1st_box=findViewById(R.id.yr_1st_box);
        CardView yr_2nd_box=findViewById(R.id.yr_2nd_box);
        CardView yr_3rd_box=findViewById(R.id.yr_3rd_box);
        CardView yr_4th_box=findViewById(R.id.yr_4th_box);
        ImageView back_btn=findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        yr_1st_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subadmin_exam_activity.this, subadmin_year_exam_activity.class);
                intent.putExtra("year_uid","yr_1st_box");
                intent.putExtra("year_txt","1st Year");
                startActivity(intent);
            }
        });
        yr_2nd_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subadmin_exam_activity.this, subadmin_year_exam_activity.class);
                intent.putExtra("year_uid","yr_2nd_box");
                intent.putExtra("year_txt","2nd Year");
                startActivity(intent);
            }
        });
        yr_3rd_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subadmin_exam_activity.this, subadmin_year_exam_activity.class);
                intent.putExtra("year_uid","yr_3rd_box");
                intent.putExtra("year_txt","3rd Year");
                startActivity(intent);
            }
        });
        yr_4th_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subadmin_exam_activity.this, subadmin_year_exam_activity.class);
                intent.putExtra("year_uid","yr_4th_box");
                intent.putExtra("year_txt","4th Year");
                startActivity(intent);
            }
        });
    }
}