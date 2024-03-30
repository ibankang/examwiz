package examwiz.ibankang.com.adminUi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import examwiz.ibankang.com.R;

public class AdminSearchFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin_search, container, false);

        EditText search_edit_text;
        ImageView search_btn;

        search_edit_text = view.findViewById(R.id.search_edit_text);
        search_btn = view.findViewById(R.id.search_btn);

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
}