package we_chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chatting")
public class Chatting {

    @Id
    @Column(name = "ch_id")
    private String chId;  //聊天id
    @Column(name = "ch_uid")
    private String chUid;  //发送方id
    @Column(name = "ch_fid")
    private String chFid;  //接收方id
    @Column(name = "ch_content")
    private String chContent; //聊天内容
    @Column(name = "ch_time")
    private String chTime; //聊天时间
    @Column(name = "ch_image")
    private String chImage;  //当前用户的聊天头像地址
    @Column(name = "category")
    private String category; //前台用来区分循环的那个对象
    @Column(name = "ch_name")
    private String chName; //发送方用户名

    public String getChId() {
        return chId;
    }

    public void setChId(String chId) {
        this.chId = chId;
    }

    public String getChUid() {
        return chUid;
    }

    public void setChUid(String chUid) {
        this.chUid = chUid;
    }

    public String getChFid() {
        return chFid;
    }

    public void setChFid(String chFid) {
        this.chFid = chFid;
    }

    public String getChContent() {
        return chContent;
    }

    public void setChContent(String chContent) {
        this.chContent = chContent;
    }

    public String getChTime() {
        return chTime;
    }

    public void setChTime(String chTime) {
        this.chTime = chTime;
    }

    public String getChImage() {
        return chImage;
    }

    public void setChImage(String chImage) {
        this.chImage = chImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }
}
