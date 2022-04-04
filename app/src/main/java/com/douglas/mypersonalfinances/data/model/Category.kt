package com.douglas.mypersonalfinances.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_category")
    @SerializedName("id_category")
    @NotNull
    val idCategory: Int,
    @ColumnInfo(name = "category_name")
    @SerializedName("category_name")
    @NotNull
    val categoryName: String
) : Serializable, Parcelable {


    constructor(parcel: Parcel) : this(
        idCategory = parcel.readInt(),
        categoryName = parcel.readString() ?: "Category Unknown"
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(idCategory)
        parcel?.writeString(categoryName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}