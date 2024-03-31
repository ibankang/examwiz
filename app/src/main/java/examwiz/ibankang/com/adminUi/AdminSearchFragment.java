package examwiz.ibankang.com.adminUi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import examwiz.ibankang.com.R;
import examwiz.ibankang.com.SubAdmin.subadmin_exam_schedule_details_adapter;
import examwiz.ibankang.com.view_model;

public class AdminSearchFragment extends Fragment {

    View view;
    String rollno = null;
    RecyclerView recyclerView;
    private FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin_search, container, false);

        EditText search_edit_text;
        ImageView search_btn;

        search_edit_text = view.findViewById(R.id.search_edit_txt);
        search_btn = view.findViewById(R.id.search_btn_img);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the search query
                String search_query = search_edit_text.getText().toString().trim();
                if (search_query.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a search query", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Search for the query
                    Toast.makeText(getContext(), "Searching for " + search_query, Toast.LENGTH_SHORT).show();
                }

                // Search for the query
                // search(search_query);
            }
        });

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        get_user_rollno();
//    }

//    private void get_exam_details_user() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault());
//        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mlinearLayoutManager);
//        ArrayList<view_model> dataholder = new ArrayList<>();
//        subadmin_exam_schedule_details_adapter wAdapter = new subadmin_exam_schedule_details_adapter(dataholder, getContext());
//        CollectionReference collectionReference = db.collection("schedule");
//        collectionReference
//                .whereEqualTo("rollno",rollno)
//                //.orderBy("date_time",Query.Direction.DESCENDING)
//                .get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult().size() > 0) {
//                        for (QueryDocumentSnapshot s : task.getResult()) {
//
//                            view_model obj = new view_model(
//                                    s.getString("roomno"),//1
//                                    s.getString("rowno"),//2
//                                    s.getString("seatno"),//3
////                                    s.getString("exam_start"),//4
////                                    //String.valueOf(dateFormat.format(s.getTimestamp("dob").toDate())),//4
////                                    s.getString("exam_end"),//5
////                                    s.getString("seating_plan_live"),//6
//                                    "",//7
//                                    "",//7
//                                    "",//7
//                                    "",//7
//                                    "",//8
//                                    "",//9
//                                    s.getString("exam_uid"));//10
//
//                            dataholder.add(obj);
//                        }
//                        recyclerView.setAdapter(wAdapter);
//                    } else {
//
//                    }
//                });
//
//    }
//
//    private void get_user_rollno() {
//
//        EditText search_roll_no_edit_txt = view.findViewById(R.id.search_edit_txt);
//        // Get Firebase user
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (firebaseUser != null) {
//            // Get user ID
//            String userId = firebaseUser.getUid();
//
//            // Get Firestore instance
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//            if (search_roll_no_edit_txt.getEditableText().toString().trim() != null) {
//
//                // Get document reference
//                DocumentReference docRef = db.collection("account").document(userId);
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                // Retrieve user data
//                                rollno = document.getString("roll_no");
//                                String name = document.getString("name");
//                                Toast.makeText(getContext(), name + "_" + rollno, Toast.LENGTH_SHORT).show();
//
//                                get_exam_details_user();
//                            }
//                        } else {
//                            Log.d("MainActivity", "Document retrieval failed:", task.getException());
//                        }
//                    }
//                });
//            }
//        }
//    }
}