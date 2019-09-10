package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "the")
public class The {
  @Id
  @Column(name = "t_id")
  private String tId;
  @Column(name = "t_name")
  private String tName;
  @Column(name = "t_porimage")
  private String tPorimage;
  @Column(name = "t_letter")
  private String tLetter;
  @Column(name = "u_id")
  private String uId;
  @Column(name = "ud_id")
  private String udId;
  @Column(name = "ci_id")
  private String CiId;

  public String gettId() {
    return tId;
  }

  public void settId(String tId) {
    this.tId = tId;
  }

  public String gettName() {
    return tName;
  }

  public void settName(String tName) {
    this.tName = tName;
  }

  public String gettPorimage() {
    return tPorimage;
  }

  public void settPorimage(String tPorimage) {
    this.tPorimage = tPorimage;
  }

  public String gettLetter() {
    return tLetter;
  }

  public void settLetter(String tLetter) {
    this.tLetter = tLetter;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getUdId() {
    return udId;
  }

  public void setUdId(String udId) {
    this.udId = udId;
  }

  public String getCiId() {
    return CiId;
  }

  public void setCiId(String ciId) {
    CiId = ciId;
  }
}
