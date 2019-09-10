package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "circle")
public class Circle {

  @Id
  @Column(name = "ci_id")
  private String ciId;
  @Column(name = "ci_content")
  private String ciContent;
  @Column(name = "ci_address")
  private String ciAddress;
  @Column(name = "ci_conimage")
  private String ciConimage;
  @Column(name = "ci_time")
  private String ciTime;
  @Column(name = "u_id")
  private String uId;
  @Column(name = "j_id")
  private String jId;
  @Column(name = "t_id")
  private String tId;

  public String getCiId() {
    return ciId;
  }

  public void setCiId(String ciId) {
    this.ciId = ciId;
  }

  public String getCiContent() {
    return ciContent;
  }

  public void setCiContent(String ciContent) {
    this.ciContent = ciContent;
  }

  public String getCiAddress() {
    return ciAddress;
  }

  public void setCiAddress(String ciAddress) {
    this.ciAddress = ciAddress;
  }

  public String getCiConimage() {
    return ciConimage;
  }

  public void setCiConimage(String ciConimage) {
    this.ciConimage = ciConimage;
  }

  public String getCiTime() {
    return ciTime;
  }

  public void setCiTime(String ciTime) {
    this.ciTime = ciTime;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String gettId() {
    return tId;
  }

  public void settId(String tId) {
    this.tId = tId;
  }

  public String getjId() {
    return jId;
  }

  public void setjId(String jId) {
    this.jId = jId;
  }
}
