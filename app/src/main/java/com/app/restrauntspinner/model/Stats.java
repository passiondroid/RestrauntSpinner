package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats implements Parcelable {

    @SerializedName("checkinsCount")
    @Expose
    private Integer checkinsCount;
    @SerializedName("usersCount")
    @Expose
    private Integer usersCount;
    @SerializedName("tipCount")
    @Expose
    private Integer tipCount;

    public Integer getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(Integer checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Integer getTipCount() {
        return tipCount;
    }

    public void setTipCount(Integer tipCount) {
        this.tipCount = tipCount;
    }


    protected Stats(Parcel in) {
        checkinsCount = in.readByte() == 0x00 ? null : in.readInt();
        usersCount = in.readByte() == 0x00 ? null : in.readInt();
        tipCount = in.readByte() == 0x00 ? null : in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (checkinsCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(checkinsCount);
        }
        if (usersCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(usersCount);
        }
        if (tipCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(tipCount);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Stats> CREATOR = new Parcelable.Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel in) {
            return new Stats(in);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };
}