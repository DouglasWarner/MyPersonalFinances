package com.douglas.mypersonalfinances.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "user")
data class UserApp(
    @PrimaryKey
    @ColumnInfo(name = "account")
    @SerializedName("userAccount")
    @NotNull
    val userAccount: String,
    @ColumnInfo(name = "password")
    @SerializedName("password")
    @NotNull
    val password: String,
    @ColumnInfo(name = "user_name")
    @SerializedName("name")
    @NotNull
    val name: String,
    @ColumnInfo(name = "keep_logged")
    @SerializedName("keepLogged")
    val keepLogged: Boolean
) : Serializable, Parcelable {

    constructor(parcel: Parcel) : this(
        userAccount = parcel.readString() ?: "",
        password = parcel.readString() ?: "",
        name = parcel.readString() ?: "",
        keepLogged = parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(userAccount)
        parcel?.writeString(password)
        parcel?.writeString(name)
        parcel?.writeByte(if (keepLogged) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserApp> {
        override fun createFromParcel(parcel: Parcel): UserApp {
            return UserApp(parcel)
        }

        override fun newArray(size: Int): Array<UserApp?> {
            return arrayOfNulls(size)
        }
    }
}