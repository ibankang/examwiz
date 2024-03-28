package examwiz.ibankang.com.SubAdmin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import examwiz.ibankang.com.R;

public class SubAdminAdapter extends RecyclerView.Adapter<SubAdminAdapter.ViewHolder>{

    Context context;
    List<ExamItemClass> items;

    public SubAdminAdapter(Context context, List<ExamItemClass> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public SubAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new View(context));
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdminAdapter.ViewHolder holder, int position) {
        ExamItemClass currentItem = items.get(position);

        holder.examDate.setText(currentItem.getExamDate());
        holder.examDay.setText(currentItem.getExamDay());
        holder.examName.setText(currentItem.getExamName());
        holder.examType.setText(currentItem.getExamType());
        holder.examTiming.setText(currentItem.getExamTiming());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView examDate,examDay,examName,examType,examTiming;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            examDate = itemView.findViewById(R.id.examDate);
            examDay = itemView.findViewById(R.id.examDay);
            examName = itemView.findViewById(R.id.examName);
            examType = itemView.findViewById(R.id.examType);
            examTiming = itemView.findViewById(R.id.examTiming);
        }
    }
}
