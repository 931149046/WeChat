package we_chat.pojo;


public class ChattingNew {

  private String Id;  //聊天id
  private String Uname;  //发送方用户名字
  private String Uimage; //发送方用户头像地址
  private String FId;  //接收方id
  private String Fimage;  //接收方用户头像地址
  private String Content; //聊天内容
  private String Time; //聊天时间
  private int Type; //区别群聊还是单聊

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public String getUname() {
    return Uname;
  }

  public void setUname(String uname) {
    Uname = uname;
  }

  public String getUimage() {
    return Uimage;
  }

  public void setUimage(String uimage) {
    Uimage = uimage;
  }

  public String getFId() {
    return FId;
  }

  public void setFId(String FId) {
    this.FId = FId;
  }

  public String getFimage() {
    return Fimage;
  }

  public void setFimage(String fimage) {
    Fimage = fimage;
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

  public String getTime() {
    return Time;
  }

  public void setTime(String time) {
    Time = time;
  }

  public int getType() {
    return Type;
  }

  public void setType(int type) {
    Type = type;
  }
}
