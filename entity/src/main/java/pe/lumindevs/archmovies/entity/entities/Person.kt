package pe.lumindevs.archmovies.entity.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import pe.lumindevs.archmovies.entity.response.PersonDetail

@Parcelize
@Entity(tableName = "People")
data class Person(
    var page: Int,
    @Embedded var personDetail: PersonDetail? = null,
    val profile_path: String?,
    val adult: Boolean,
    @PrimaryKey val id: Int,
    val name: String,
    val popularity: Float
) : Parcelable