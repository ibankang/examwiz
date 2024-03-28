package examwiz.ibankang.com.adminUi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import examwiz.ibankang.com.R;
import examwiz.ibankang.com.databinding.FragmentAdminHomeBinding;


public class AdminHomeFragment extends Fragment {

    FragmentAdminHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAdminHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}