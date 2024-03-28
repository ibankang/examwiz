package examwiz.ibankang.com.adminUi;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import examwiz.ibankang.com.R;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private Context context;
    private List<ExamDetails> examItemClassList;
    private int[] colors;
    private int currentColor = 0;

    public AdminAdapter(Context context, List<ExamDetails> examItemClassList) {
        this.context = context;
        this.examItemClassList = examItemClassList;
        colors = new int[]{R.color.hot_red, R.color.hot_indigo, R.color.hot_green, R.color.hot_blue, R.color.hot_red, R.color.hot_indigo, R.color.hot_green, R.color.hot_blue};
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminViewHolder((ViewGroup) LayoutInflater.from(context).inflate(R.layout.exam_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        ExamDetails examDetails = examItemClassList.get(position);
        holder.examName.setText(examDetails.getExamName());
        holder.examYear.setText(examDetails.getExamYear());
        holder.examType.setText(examDetails.getExamType());
        holder.examDate.setText(examDetails.getExamDate());
        holder.examTime.setText(examDetails.getExamTime());

        holder.card_exam_details.setBackgroundTintList(ColorStateList.valueOf(holder.itemView.getResources().getColor(colors[currentColor])));
        currentColor++;
        if (currentColor == 8) {
            currentColor = 0;
        }
    }

    @Override
    public int getItemCount() {
        return examItemClassList.size();
    }

    public static class AdminViewHolder extends RecyclerView.ViewHolder {

        TextView examName, examYear, examType, examDate, examTime;
        View card_exam_details;

        public AdminViewHolder(@NonNull ViewGroup parent) {
            super(parent);

            examName = parent.findViewById(R.id.tv_subject_name);
            examYear = parent.findViewById(R.id.tv_exam_year);
            examType = parent.findViewById(R.id.tv_exam_type);
            examDate = parent.findViewById(R.id.tv_exam_date);
            examTime = parent.findViewById(R.id.tv_exam_time);

            card_exam_details = itemView.findViewById(R.id.card_exam_details);
        }
    }
}
