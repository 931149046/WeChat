package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "backimage")
public class Backimage {

  @Id
  @Column(name = "b_id")
  private String bId;
  @Column(name = "b_backimg")
  private String bBackimg;
  @Column(name = "u_id")
  private String uId;


  public String getBId() {
    return bId;
  }

  public void setBId(String bId) {
    this.bId = bId;
  }


  public String getBBackimg() {
    return bBackimg;
  }

  public void setBBackimg(String bBackimg) {
    this.bBackimg = bBackimg;
  }


  public String getUId() {
    return uId;
  }

  public void setUId(String uId) {
    this.uId = uId;
  }

}
