package com.douglas.mypersonalfinances.data.model.transaction

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.annotations.NotNull
import org.joda.time.LocalDateTime

/**
 * Core entity data for transaction table
 * @see ExpenseTransaction
 * @see IncomeTransaction
 */
@Entity(
    tableName = "transaction"
)
@Parcelize
@Serializable
open class MyTransaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_transaction")
    @SerializedName("id_transaction")
    val idTransaction: Int = 0,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    @NotNull
    @Contextual
    val dateTime: LocalDateTime,
    @ColumnInfo(name = "amount", defaultValue = "0")
    @SerializedName("amount")
    val amount: Float,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,
    @ColumnInfo(name = "category")
    @SerializedName("category")
    @NotNull
    val category: String,
    @ColumnInfo(name = "type_transaction", defaultValue = "Unknown")
    @SerializedName("type_transaction")
    @NotNull
    val type: TypeTransaction,
    @ColumnInfo(name = "is_sync")
    @SerializedName("is_sync")
    val isSync: Boolean = false,
    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    val idUser: Int
) : Parcelable {

    companion object : Parceler<MyTransaction> {

        const val TAG = "MyTransaction"

        override fun MyTransaction.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(idTransaction)
            parcel.writeFloat(amount)
            parcel.writeString(description)
            parcel.writeSerializable(category)
            parcel.writeByte(if (isSync) 1 else 0)
            parcel.writeInt(idUser)
        }

        override fun create(parcel: Parcel): MyTransaction {
            return MyTransaction(
                idTransaction = parcel.readInt(),
                dateTime = LocalDateTime(parcel.readLong()),
                amount = parcel.readFloat(),
                description = parcel.readString() ?: "",
                category = parcel.readString() ?: "",
                type = TypeTransaction.valueOf(parcel.readString() ?: "Unknown"),
                isSync = parcel.readByte() != 0.toByte(),
                idUser = parcel.readInt()
            )
        }
    }
}