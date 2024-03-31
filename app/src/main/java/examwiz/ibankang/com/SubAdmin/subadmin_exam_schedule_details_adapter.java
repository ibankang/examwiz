package examwiz.ibankang.com.SubAdmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import examwiz.ibankang.com.R;
import examwiz.ibankang.com.view_model;

public class subadmin_exam_schedule_details_adapter extends RecyclerView.Adapter<subadmin_exam_schedule_details_adapter.viewholder> {

    ArrayList<view_model> dataholder;
    Context context;

    public subadmin_exam_schedule_details_adapter(ArrayList<view_model> dataholder, Context context){
        this.dataholder = dataholder;
        this.context = context;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_exam_schedule_list, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        final view_model temp = dataholder.get(position);
        holder.exam_room_no_txt.setText(temp.getText1());
        holder.exam_row_no_txt.setText(temp.getText2());
        holder.exam_seat_no_txt.setText(temp.getText3());
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("exam");
        collectionReference
                .whereEqualTo("exam_uid",temp.getText10()).limit(1)
                //.orderBy("date_time",Query.Direction.DESCENDING)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult().size()>0){
                        for (QueryDocumentSnapshot s : task.getResult()){


                                    holder.exam_title_txt.setText(s.getString("exam_title"));//1
                                    holder.exam_category_txt.setText(s.getString("exam_category"));
                                    holder.exam_start_txt.setText(s.getString("exam_start"));
                                    holder.exam_end_txt.setText(s.getString("exam_end"));

                        }

                    }else
                    {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

       LinearLayout add_new_layout;
        TextView  exam_room_no_txt, exam_title_txt, exam_category_txt, exam_start_txt, exam_end_txt, exam_row_no_txt, exam_seat_no_txt;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            add_new_layout = itemView.findViewById(R.id.add_new_layout);
            exam_title_txt = itemView.findViewById(R.id.exam_title_txt);
            exam_category_txt = itemView.findViewById(R.id.exam_category_txt);
            exam_start_txt = itemView.findViewById(R.id.exam_start_txt);
            exam_end_txt = itemView.findViewById(R.id.exam_end_txt);

            exam_room_no_txt = itemView.findViewById(R.id.exam_room_no_txt);
            exam_row_no_txt = itemView.findViewById(R.id.exam_row_no_txt);
            exam_seat_no_txt = itemView.findViewById(R.id.exam_seat_no_txt);
        }
    }
}
