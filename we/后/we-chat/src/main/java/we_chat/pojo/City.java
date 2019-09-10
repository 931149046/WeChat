package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "pid")
  private String pid;
  @Column(name = "cityname")
  private String cityname;
  @Column(name = "type")
  private String type;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }


  public String getCityname() {
    return cityname;
  }

  public void setCityname(String cityname) {
    this.cityname = cityname;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
