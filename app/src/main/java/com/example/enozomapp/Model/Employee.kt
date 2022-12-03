package com.example.enozomapp.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "employee")
@Parcelize
class Employee : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray? = null
    var skills: List<String?>? = null
    var name: String? = null
    var email: String? = null
}