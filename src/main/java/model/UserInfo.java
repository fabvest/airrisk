package model;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("arrayDrag")
    @Expose
    public ArrayList<ArrayDrag> arrayDrag = null;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "arrayDrag=" + arrayDrag +
                ", orgControl='" + orgControl + '\'' +
                ", cityControl='" + cityControl + '\'' +
                ", districtControl='" + districtControl + '\'' +
                ", streetControl='" + streetControl + '\'' +
                ", houseControl=" + houseControl +
                ", catPeopleControl='" + catPeopleControl + '\'' +
                ", startPerMonthControl=" + startPerMonthControl +
                ", startPerYearControl=" + startPerYearControl +
                ", endPerMonthControl=" + endPerMonthControl +
                ", endPerYearControl=" + endPerYearControl +
                ", dateControl='" + dateControl + '\'' +
                '}';
    }

    public ArrayList<ArrayDrag> getArrayDrag() {
        return arrayDrag;
    }

    public void setArrayDrag(ArrayList<ArrayDrag> arrayDrag) {
        this.arrayDrag = arrayDrag;
    }

    public String getOrgControl() {
        return orgControl;
    }

    public void setOrgControl(String orgControl) {
        this.orgControl = orgControl;
    }

    public String getCityControl() {
        return cityControl;
    }

    public void setCityControl(String cityControl) {
        this.cityControl = cityControl;
    }

    public String getDistrictControl() {
        return districtControl;
    }

    public void setDistrictControl(String districtControl) {
        this.districtControl = districtControl;
    }

    public String getStreetControl() {
        return streetControl;
    }

    public void setStreetControl(String streetControl) {
        this.streetControl = streetControl;
    }

    public Integer getHouseControl() {
        return houseControl;
    }

    public void setHouseControl(Integer houseControl) {
        this.houseControl = houseControl;
    }

    public String getCatPeopleControl() {
        return catPeopleControl;
    }

    public void setCatPeopleControl(String catPeopleControl) {
        this.catPeopleControl = catPeopleControl;
    }

    public Integer getStartPerMonthControl() {
        return startPerMonthControl;
    }

    public void setStartPerMonthControl(Integer startPerMonthControl) {
        this.startPerMonthControl = startPerMonthControl;
    }

    public Integer getStartPerYearControl() {
        return startPerYearControl;
    }

    public void setStartPerYearControl(Integer startPerYearControl) {
        this.startPerYearControl = startPerYearControl;
    }

    public Integer getEndPerMonthControl() {
        return endPerMonthControl;
    }

    public void setEndPerMonthControl(Integer endPerMonthControl) {
        this.endPerMonthControl = endPerMonthControl;
    }

    public Integer getEndPerYearControl() {
        return endPerYearControl;
    }

    public void setEndPerYearControl(Integer endPerYearControl) {
        this.endPerYearControl = endPerYearControl;
    }

    public String getDateControl() {
        return dateControl;
    }

    public void setDateControl(String dateControl) {
        this.dateControl = dateControl;
    }
}