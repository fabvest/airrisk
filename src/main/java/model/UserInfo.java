package model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("arrayDrag")
    @Expose
    public List<ArrayDrag> arrayDrag = null;
    @SerializedName("orgControl")
    @Expose
    public String orgControl;
    @SerializedName("cityControl")
    @Expose
    public String cityControl;
    @SerializedName("districtControl")
    @Expose
    public String districtControl;
    @SerializedName("streetControl")
    @Expose
    public String streetControl;
    @SerializedName("houseControl")
    @Expose
    public Integer houseControl;
    @SerializedName("catPeopleControl")
    @Expose
    public String catPeopleControl;
    @SerializedName("startPerMonthControl")
    @Expose
    public Integer startPerMonthControl;
    @SerializedName("startPerYearControl")
    @Expose
    public Integer startPerYearControl;
    @SerializedName("endPerMonthControl")
    @Expose
    public Integer endPerMonthControl;
    @SerializedName("endPerYearControl")
    @Expose
    public Integer endPerYearControl;
    @SerializedName("dateControl")
    @Expose
    public String dateControl;

}