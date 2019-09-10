package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inform")
public class Inform {

  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "f_id")
  private String fId;
  @Column(name = "f_name")
  private String fName;
  @Column(name = "content")
  private String content;
  @Column(name = "j_id")
  private String jId;
  @Column(name = "state")
  private String state;
  @Column(name = "findstate")
  private String findstate;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getFId() {
    return fId;
  }

  public void setFId(String fId) {
    this.fId = fId;
  }


  public String getFName() {
    return fName;
  }

  public void setFName(String fName) {
    this.fName = fName;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getJId() {
    return jId;
  }

  public void setJId(String jId) {
    this.jId = jId;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public String getFindstate() {
    return findstate;
  }

  public void setFindstate(String findstate) {
    this.findstate = findstate;
  }

}
