package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "chat")
public class Chat {
  @Id
  @Column(name = "c_id")
  private String cId;
  @Column(name = "d_Remark")
  private String dRemark;
  @Column(name = "c_porimage")
  private String cPorimage;
  @Column(name = "c_time")
  private String cTime;
  @Column(name = "d_idpk")
  private String dIdPk;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getdRemark() {
        return dRemark;
    }

    public void setdRemark(String dRemark) {
        this.dRemark = dRemark;
    }

    public String getcPorimage() {
        return cPorimage;
    }

    public void setcPorimage(String cPorimage) {
        this.cPorimage = cPorimage;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getdIdPk() {
        return dIdPk;
    }

    public void setdIdPk(String dIdPk) {
        this.dIdPk = dIdPk;
    }
}
