package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hours implements Parcelable {

    @SerializedName("isOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("isLocalHoliday")
    @Expose
    private Boolean isLocalHoliday;

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsLocalHoliday() {
        return isLocalHoliday;
    }

    public void setIsLocalHoliday(Boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }


    protected Hours(Parcel in) {
        byte isOpenVal = in.readByte();
        isOpen = isOpenVal == 0x02 ? null : isOpenVal != 0x00;
        byte isLocalHolidayVal = in.readByte();
        isLocalHoliday = isLocalHolidayVal == 0x02 ? null : isLocalHolidayVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (isOpen == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isOpen ? 0x01 : 0x00));
        }
        if (isLocalHoliday == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isLocalHoliday ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Hours> CREATOR = new Parcelable.Creator<Hours>() {
        @Override
        public Hours createFromParcel(Parcel in) {
            return new Hours(in);
        }

        @Override
        public Hours[] newArray(int size) {
            return new Hours[size];
        }
    };
}