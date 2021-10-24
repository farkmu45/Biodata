package com.example.biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.biodata.databinding.FragmentDetailBinding;
import com.example.biodata.model.Student;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int studentId = DetailFragmentArgs.fromBundle(getArguments()).getStudentId();
        Student student = MainActivity.db.studentDao().selectStudentWhereId(studentId);

        binding.name.setText(student.name);
        binding.number.setText(String.valueOf(student.id));
        binding.address.setText(student.address);
        binding.birthDate.setText(student.birth_date);
        binding.gender.setText(student.gender);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}