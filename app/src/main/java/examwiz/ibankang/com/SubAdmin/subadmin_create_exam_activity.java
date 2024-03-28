package examwiz.ibankang.com.SubAdmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import examwiz.ibankang.com.R;

public class subadmin_create_exam_activity extends AppCompatActivity {
    private static final int REQUEST_CODE_CSV = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subadmin_create_exam_activity);

        ImageView upload_img = findViewById(R.id.upload_img);
        upload_img.setOnClickListener(v -> {
            // Open file picker to select CSV file
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("text/csv"); // Set the MIME type for CSV files
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE_CSV);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CSV && resultCode == RESULT_OK && data != null) {
            // Get selected CSV file URI
            Uri csvUri = data.getData();

            // Upload CSV file to Firebase Storage
            uploadCsvFile(csvUri);
        }
    }

    private void uploadCsvFile(Uri csvUri) {
        if (csvUri != null) {
            ProgressBar progressDialog = new ProgressBar(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setVisibility(ProgressBar.VISIBLE);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            // Create a unique filename for the image
            String file_name = "schedule_file" + System.currentTimeMillis() + ".csv";

            StorageReference csvRef = storageRef.child("schedule_file").child(file_name);

            csvRef.putFile(csvUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // CSV file upload successful
                        Toast.makeText(subadmin_create_exam_activity.this, "CSV file uploaded successfully", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(exception -> {
                        // CSV file upload failed
                        Log.e("Firebase", "Failed to upload CSV file", exception);
                        Toast.makeText(subadmin_create_exam_activity.this, "Failed to upload CSV file", Toast.LENGTH_SHORT).show();
                    });
            progressDialog.setVisibility(ProgressBar.INVISIBLE);
        }
    }
}