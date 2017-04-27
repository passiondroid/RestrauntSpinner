package com.app.restrauntspinner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("ratingColor")
    @Expose
    private String ratingColor;
    @SerializedName("ratingSignals")
    @Expose
    private Integer ratingSignals;
    @SerializedName("beenHere")
    @Expose
    private BeenHere beenHere;
    @SerializedName("hours")
    @Expose
    private Hours hours;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("hereNow")
    @Expose
    private HereNow hereNow;
    @SerializedName("featuredPhotos")
    @Expose
    private FeaturedPhotos featuredPhotos;
    @SerializedName("allowMenuUrlEdit")
    @Expose
    private Boolean allowMenuUrlEdit;
    @SerializedName("price")
    @Expose
    private Price price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public Integer getRatingSignals() {
        return ratingSignals;
    }

    public void setRatingSignals(Integer ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    public BeenHere getBeenHere() {
        return beenHere;
    }

    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    public FeaturedPhotos getFeaturedPhotos() {
        return featuredPhotos;
    }

    public void setFeaturedPhotos(FeaturedPhotos featuredPhotos) {
        this.featuredPhotos = featuredPhotos;
    }

    public Boolean getAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(Boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }


    protected Venue(Parcel in) {
        id = in.readString();
        name = in.readString();
        location = (Location) in.readValue(Location.class.getClassLoader());
        if (in.readByte() == 0x01) {
            categories = new ArrayList<Category>();
            in.readList(categories, Category.class.getClassLoader());
        } else {
            categories = null;
        }
        byte verifiedVal = in.readByte();
        verified = verifiedVal == 0x02 ? null : verifiedVal != 0x00;
        stats = (Stats) in.readValue(Stats.class.getClassLoader());
        rating = in.readByte() == 0x00 ? null : in.readDouble();
        ratingColor = in.readString();
        ratingSignals = in.readByte() == 0x00 ? null : in.readInt();
        beenHere = (BeenHere) in.readValue(BeenHere.class.getClassLoader());
        hours = (Hours) in.readValue(Hours.class.getClassLoader());
        photos = (Photos) in.readValue(Photos.class.getClassLoader());
        hereNow = (HereNow) in.readValue(HereNow.class.getClassLoader());
        featuredPhotos = (FeaturedPhotos) in.readValue(FeaturedPhotos.class.getClassLoader());
        byte allowMenuUrlEditVal = in.readByte();
        allowMenuUrlEdit = allowMenuUrlEditVal == 0x02 ? null : allowMenuUrlEditVal != 0x00;
        price = (Price) in.readValue(Price.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeValue(location);
        if (categories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(categories);
        }
        if (verified == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (verified ? 0x01 : 0x00));
        }
        dest.writeValue(stats);
        if (rating == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(rating);
        }
        dest.writeString(ratingColor);
        if (ratingSignals == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(ratingSignals);
        }
        dest.writeValue(beenHere);
        dest.writeValue(hours);
        dest.writeValue(photos);
        dest.writeValue(hereNow);
        dest.writeValue(featuredPhotos);
        if (allowMenuUrlEdit == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (allowMenuUrlEdit ? 0x01 : 0x00));
        }
        dest.writeValue(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
}