package main;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kreska on 08.01.17.
 */
public class Trip {
    private String country;
    private int duration;
    private float cost;

    public String getCountry() {
        return country;
    }

    public int getDuration() {
        return duration;
    }

    public float getCost() {
        return cost;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

   /* private transient String country_code;
    private transient String delegacja;
    private transient String miasto;
    private transient String id;
    private transient String wniosek_nr;
    private transient String od;
    @SerializedName("do")
    private transient String doo;
    private transient String koszt_transport;
    private transient String koszt_dieta;*/
}
