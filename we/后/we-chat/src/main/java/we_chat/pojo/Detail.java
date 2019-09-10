package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detail")
public class Detail {

  @Id
  @Column(name = "d_id")
  private String dId;
  @Column(name = "d_remark")
  private String dRemark;
  @Column(name = "d_number")
  private String dNumber;
  @Column(name = "d_area")
  private String dArea;
  @Column(name = "d_image")
  private String dImage;
  @Column(name = "d_sex")
  private String dSex;
  @Column(name = "t_id")
  private String tId;

  public String getdId() {
    return dId;
  }

  public void setdId(String dId) {
    this.dId = dId;
  }

  public String getdRemark() {
    return dRemark;
  }

  public void setdRemark(String dRemark) {
    this.dRemark = dRemark;
  }

  public String getdNumber() {
    return dNumber;
  }

  public void setdNumber(String dNumber) {
    this.dNumber = dNumber;
  }

  public String getdArea() {
    return dArea;
  }

  public void setdArea(String dArea) {
    this.dArea = dArea;
  }

  public String getdImage() {
    return dImage;
  }

  public void setdImage(String dImage) {
    this.dImage = dImage;
  }

  public String gettId() {
    return tId;
  }

  public void settId(String tId) {
    this.tId = tId;
  }

  public String getdSex() {
    return dSex;
  }

  public void setdSex(String dSex) {
    this.dSex = dSex;
  }
}
