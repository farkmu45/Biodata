package com.example.biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.biodata.databinding.FragmentFormBinding;
import com.example.biodata.model.Student;
import com.example.biodata.model.StudentDao;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private StudentDao studentDao;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = FormFragmentArgs.fromBundle(getArguments()).getStudentId();

        studentDao = MainActivity.db.studentDao();
        Student selectedStudent = studentDao.selectStudentWhereId(id);

        binding.name.getEditText().setOnClickListener(view1 -> {
            binding.name.setError(null);
        });

        if (id != 0) {
            binding.addButton.setText("Update");
            setData(selectedStudent);
        } else {
            binding.addButton.setText("Simpan");
        }
        binding.addButton.setOnClickListener(view1 -> {
            addData(selectedStudent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    private void addData(Student selectedStudent) {
        if (isDataValid()) {

            String gender = "";
            Student student;

            switch (binding.gender.getCheckedRadioButtonId()) {
                case R.id.radio_button_male:
                    gender = "Laki-laki";
                    break;
                case R.id.radio_button_female:
                    gender = "Perempuan";
                    break;
            }

            student = selectedStudent == null ? new Student() : selectedStudent;

            student.address = binding.address.getEditText().getText().toString();
            student.birth_date = binding.birthDate.getEditText().getText().toString();
            student.name = binding.name.getEditText().getText().toString();
            student.gender = gender;

            if (selectedStudent == null) studentDao.insertMany(student);
            else studentDao.updateMany(student);

            Toast.makeText(getActivity(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).navigateUp();
        }
    }

    private boolean isDataValid() {
        boolean valid = true;
        if (binding.name.getEditText().getText().toString().isEmpty()) {
            binding.name.setError("Tidak boleh kosong");
            valid = false;
        } else {
            binding.name.setError(null);
        }

        if (binding.address.getEditText().getText().toString().isEmpty()) {
            binding.address.setError("Tidak boleh kosong");
            valid = false;
        } else {
            binding.address.setError(null);
        }

        if (binding.birthDate.getEditText().getText().toString().isEmpty()) {
            binding.birthDate.setError("Tidak boleh kosong");
            valid = false;
        } else {
            binding.birthDate.setError(null);
        }

        return valid;
    }

    private void setData(Student student) {
        binding.address.getEditText().setText(student.address);
        binding.birthDate.getEditText().setText(student.birth_date);
        binding.name.getEditText().setText(student.name);
        if (student.gender.equalsIgnoreCase("Laki-laki")) {
            binding.radioButtonMale.setChecked(true);
        } else {
            binding.radioButtonFemale.setChecked(true);
        }
    }

}