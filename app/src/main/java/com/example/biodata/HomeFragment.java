package com.example.biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.biodata.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.actionButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).navigate(R.id.action_homeFragment_to_formFragment);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        StudentAdapter adapter = new StudentAdapter(MainActivity.db.studentDao().get());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.list.getContext(), layoutManager.getOrientation());
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(layoutManager);
        binding.list.addItemDecoration(dividerItemDecoration);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}