package we_chat.pojo;


public class ChatContentObj {
    private String name; //返回的用户名
    private String content;  //返回的内容
    private String image; //返回的头像
    private String date; //聊天的日期
    private String category; //前台用来区分循环的那个对象

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
