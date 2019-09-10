package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addfriend")
public class Addfriend {

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

  public String getfId() {
    return fId;
  }

  public void setfId(String fId) {
    this.fId = fId;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getjId() {
    return jId;
  }

  public void setjId(String jId) {
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
