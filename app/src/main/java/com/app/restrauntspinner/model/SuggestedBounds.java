package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestedBounds implements Parcelable {

    @SerializedName("ne")
    @Expose
    private Ne ne;
    @SerializedName("sw")
    @Expose
    private Sw sw;

    public Ne getNe() {
        return ne;
    }

    public void setNe(Ne ne) {
        this.ne = ne;
    }

    public Sw getSw() {
        return sw;
    }

    public void setSw(Sw sw) {
        this.sw = sw;
    }


    protected SuggestedBounds(Parcel in) {
        ne = (Ne) in.readValue(Ne.class.getClassLoader());
        sw = (Sw) in.readValue(Sw.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ne);
        dest.writeValue(sw);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SuggestedBounds> CREATOR = new Parcelable.Creator<SuggestedBounds>() {
        @Override
        public SuggestedBounds createFromParcel(Parcel in) {
            return new SuggestedBounds(in);
        }

        @Override
        public SuggestedBounds[] newArray(int size) {
            return new SuggestedBounds[size];
        }
    };
}