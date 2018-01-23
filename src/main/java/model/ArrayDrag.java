package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrayDrag {
    @SerializedName("drug")
    @Expose
    public Integer drug;
    @SerializedName("unit")
    @Expose
    public Integer unit;
    @SerializedName("concentration")
    @Expose
    public Double concentration;

}