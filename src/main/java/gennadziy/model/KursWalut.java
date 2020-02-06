package gennadziy.model;

import lombok.Data;

import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
//@Data
@Table(name = "mytable")
public class KursWalut {

//
    @Id
    @SerializedName("Cur_ID")
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Cur_ID")
    private Integer curID;
    @SerializedName("Date")
    @Expose
    @Column(name = "Date")
    private String date;
    @SerializedName("Cur_Abbreviation")
    @Expose
    @Column(name = "Cur_Abbreviation")
    private String curAbbreviation;
    @SerializedName("Cur_Scale")
    @Expose
    private Integer curScale;
    @SerializedName("Cur_Name")
    @Expose
    private String curName;
    @Column(name = "Cur_OfficialRate")
    @SerializedName("Cur_OfficialRate")
    @Expose
    private Double Cur_OfficialRate;

    public Integer getCurID() {
        return curID;
    }

    public void setCurID(Integer curID) {
        this.curID = curID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public Integer getCurScale() {
        return curScale;
    }

    public void setCurScale(Integer curScale) {
        this.curScale = curScale;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public Double getCurOfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCurOfficialRate(Double curOfficialRate) {
        this.Cur_OfficialRate = Cur_OfficialRate;
    }

    @Override
    public String toString () {
        return "KursWalut{" +
                "curID=" + curID +
                ", date='" + date + '\'' +
                ", curAbbreviation='" + curAbbreviation + '\'' +
                ", curScale=" + curScale +
                ", curName='" + curName + '\'' +
                ", curOfficialRate=" + Cur_OfficialRate +
                '}';
    }
}
//    Cur_ID,Date,Cur_Abbreviation,Cur_Scale,Cur_Name,cur_official_rate
