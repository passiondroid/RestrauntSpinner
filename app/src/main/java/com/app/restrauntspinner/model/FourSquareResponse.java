package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FourSquareResponse implements Parcelable{

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    protected FourSquareResponse(Parcel in) {
        meta = (Meta) in.readValue(Meta.class.getClassLoader());
        response = (Response) in.readValue(Response.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(meta);
        dest.writeValue(response);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FourSquareResponse> CREATOR = new Parcelable.Creator<FourSquareResponse>() {
        @Override
        public FourSquareResponse createFromParcel(Parcel in) {
            return new FourSquareResponse(in);
        }

        @Override
        public FourSquareResponse[] newArray(int size) {
            return new FourSquareResponse[size];
        }
    };

}