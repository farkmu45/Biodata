package com.example.biodata.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "birth_date")
    public String birth_date;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "gender")
    public String gender;
}
