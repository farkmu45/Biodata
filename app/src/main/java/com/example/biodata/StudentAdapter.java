package com.example.biodata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biodata.model.Student;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> localData;

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public final TextView text;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.student_name);
        }
    }

    public StudentAdapter(List<Student> dataSet) {
        localData = dataSet;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.text.setText(localData.get(position).name);
        holder.text.setOnClickListener(view -> {
            showDialog(view.getContext(), position, view);
        });
    }

    @Override
    public int getItemCount() {
        return localData.size();
    }

    private void showDialog(Context context, int position, View view) {
        CharSequence[] items = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
        new MaterialAlertDialogBuilder(context).setTitle("Pilihan").setItems(items, (dialogInterface, i) -> {
            switch (i) {
                case 0:
                    HomeFragmentDirections.ActionHomeFragmentToDetailFragment actionToDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(localData.get(position).id);
                    Navigation.findNavController(view).navigate(actionToDetail);
                    break;
                case 1:
                    HomeFragmentDirections.ActionHomeFragmentToFormFragment actionToForm = HomeFragmentDirections.actionHomeFragmentToFormFragment().setStudentId(localData.get(position).id);
                    Navigation.findNavController(view).navigate(actionToForm);
                    break;
                case 2:
                    MainActivity.db.studentDao().deleteMany(localData.get(position));
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_self);
                    break;
            }
        }).show();
    }

}
