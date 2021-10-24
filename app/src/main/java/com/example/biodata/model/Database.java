package com.example.biodata.model;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Student.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract StudentDao studentDao();
}
