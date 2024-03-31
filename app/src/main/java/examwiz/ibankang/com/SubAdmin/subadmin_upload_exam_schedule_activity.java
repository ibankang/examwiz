package examwiz.ibankang.com.SubAdmin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

import examwiz.ibankang.com.R;

public class subadmin_upload_exam_schedule_activity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;
    private static final int REQUEST_PERMISSION_CODE = 123;
    private static final String TAG = "UploadCSVActivity";

    private FirebaseFirestore db;
    public String guid = null, exam_uid = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subadmin_upload_exam_schedule_activity);

        exam_uid = getIntent().getStringExtra("exam_uid");

        db = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        // Get user ID
        guid = firebaseUser.getUid();
        openFilePicker();
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a CSV file"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_SELECT_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    if (inputStream != null) {
                        readCSVFile(inputStream);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error reading CSV file", e);
                }
            }
        }
    }

    private void readCSVFile(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // Read the first line to get column names
            String headerLine = reader.readLine();
            if (headerLine != null) {
                // Parse column names
                String[] columns = headerLine.split(",");
                while ((headerLine = reader.readLine()) != null) {
                    // Parse CSV data and save to Firebase Firestore
                     saveToFirestore(parseCSV(columns, headerLine));

                }
            }
            reader.close();
        } catch (IOException e) {
            Log.e(TAG, "Error reading CSV file", e);
        }
    }

    private HashMap<String, String> parseCSV(String[] columns, String line) {
        // Parse CSV line and return a HashMap
        HashMap<String, String> data = new HashMap<>();
        String[] values = line.split(",");
        for (int i = 0; i < columns.length && i < values.length; i++) {
            // Use column names as keys
            data.put(columns[i], values[i]);
            data.put("exam_uid",exam_uid);
            data.put("date_time", String.valueOf(new Date()));
        }
        return data;
    }

    private void saveToFirestore(HashMap<String, String> data) {
        // Save data to Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("schedule")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    Toast.makeText(subadmin_upload_exam_schedule_activity.this, "CSV data uploaded successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error adding document", e);
                    Toast.makeText(subadmin_upload_exam_schedule_activity.this, "Failed to upload CSV data", Toast.LENGTH_SHORT).show();
                });
    }
}