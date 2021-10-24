package com.example.biodata.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> get();

    @Query("SELECT * FROM student WHERE id = :studentId")
    public Student selectStudentWhereId(int studentId);

    @Insert
    public void insertMany(Student... students);

    @Update
    public void updateMany(Student... students);

    @Delete
    public void deleteMany(Student... students);
}
