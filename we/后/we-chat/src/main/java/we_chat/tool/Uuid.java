package we_chat.tool;


import java.util.Random;
import java.util.UUID;

public class Uuid {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString(); //转化为String对象
        return uuid.replace("-", ""); //因为UUID本身为32位只是生成时多了“-”，所以将它们去点掉就可
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            System.out.println(Uuid.getUUID());
        }
    }
}