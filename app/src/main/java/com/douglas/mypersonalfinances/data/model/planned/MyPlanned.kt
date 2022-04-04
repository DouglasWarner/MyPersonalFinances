package com.douglas.mypersonalfinances.data.model.planned

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.douglas.mypersonalfinances.data.model.Category
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import org.joda.time.LocalDateTime
import java.io.Serializable

@Entity(
    tableName = "planned", primaryKeys = ["month", "category"]
)
open class MyPlanned(
    @ColumnInfo(name = "month")
    @SerializedName("month")
    @NotNull
    val month: LocalDateTime,
    @ColumnInfo(name = "category")
    @SerializedName("category")
    @NotNull
    val category: String,
    @ColumnInfo(name = "type_planned", defaultValue = "Unknown")
    @SerializedName("type_planned")
    val type: TypeTransaction,
    @ColumnInfo(name = "planned", defaultValue = "0")
    @SerializedName("planned")
    val planned: Float,
    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    val idUser: Int
) : Serializable, Parcelable {

    constructor(parcel: Parcel) : this(
        month = LocalDateTime(parcel.readLong()),
        category = parcel.readString() ?: "",
        type = TypeTransaction.valueOf(parcel.readString() ?: "Unknown"),
        planned = parcel.readFloat(),
        idUser = parcel.readInt()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeLong(month.toDate().time)
        parcel?.writeString(type.name)
        parcel?.writeFloat(planned)
        parcel?.writeSerializable(category)
        parcel?.writeInt(idUser)
    }

    companion object CREATOR : Parcelable.Creator<MyPlanned> {

        override fun createFromParcel(parcel: Parcel): MyPlanned {
            return MyPlanned(parcel)
        }

        override fun newArray(size: Int): Array<MyPlanned?> {
            return arrayOfNulls(size)
        }
    }
}