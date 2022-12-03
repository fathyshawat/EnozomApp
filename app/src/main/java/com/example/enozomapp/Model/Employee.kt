package com.example.enozomapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "employee")
class Employee {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray? = null
    var skills: ArrayList<String?>? = null
    var name: String? = null
    var email: String? = null
}