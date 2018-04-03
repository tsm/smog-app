package com.tomszom.smogapp.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by tsm on 31/03/2018
 */
data class Station(val id: Long = Long.MIN_VALUE,
                   val stationName: String = "",
                   val gegrLat: String = "",
                   val gegrLon: String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(stationName)
        parcel.writeString(gegrLat)
        parcel.writeString(gegrLon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Station> {
        override fun createFromParcel(parcel: Parcel): Station {
            return Station(parcel)
        }

        override fun newArray(size: Int): Array<Station?> {
            return arrayOfNulls(size)
        }
    }
}