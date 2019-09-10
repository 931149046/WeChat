package we_chat.pojo;

//页面新朋友界面需要的实体类
public class AddState {
    private String id;
    private String image; //被添加人的头像
    private String name; //被添加人的名字
    private String value; //发给被添加人的内容
    private String state; //对方是否同意的状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
