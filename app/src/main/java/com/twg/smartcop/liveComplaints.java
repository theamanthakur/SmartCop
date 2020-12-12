package com.twg.smartcop;

import com.google.firebase.database.PropertyName;

public class liveComplaints {
    public String imageName;
    public String imageURL;
    public  String location;
    public  String time;
    public liveComplaints() {
    }
    public liveComplaints(String imageName, String imageURL, String location, String time) {
        this.imageName = imageName;
        this.imageURL = imageURL;
        this.location = location;
        this.time = time;
    }



    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    @PropertyName("imageURL")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
