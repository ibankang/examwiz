package examwiz.ibankang.com.adminUi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import examwiz.ibankang.com.R;
import examwiz.ibankang.com.databinding.FragmentAdminHomeBinding;

public class AdminHomeFragment extends Fragment {

    FragmentAdminHomeBinding binding;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAdminHomeBinding.inflate(inflater, container, false);

        recyclerView = binding.rvExam;

        List<ExamDetails> examDetailsList = new ArrayList<>();
        examDetailsList.add(new ExamDetails("Mathematics", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Physics", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Chemistry", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Biology", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("English", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Agric", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Economics", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Government", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("Literature", "WAEC", "2021", "12th May", "10:00am"));
        examDetailsList.add(new ExamDetails("CRS", "WAEC", "2021", "12th May", "10:00am"));

        AdminAdapter adminAdapter = new AdminAdapter(getContext(), examDetailsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adminAdapter);

        ImageSlider imageSlider = binding.imageSlider;
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.wallpaper1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.wallpaper1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.wallpaper1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.wallpaper1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.wallpaper1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.wallpaper1, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        return binding.getRoot();
    }
}