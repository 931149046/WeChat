package we_chat.tool;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Initial {

    // 得到中文首字母
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        switch (str.toUpperCase()) { //判断进来的全部转换大写
            case "A":
                convert = "A";
                break;
            case "B":
                convert = "B";
                break;
            case "C":
                convert = "C";
                break;
            case "D":
                convert = "D";
                break;
            case "E":
                convert = "E";
                break;
            case "F":
                convert = "F";
                break;
            case "G":
                convert = "G";
                break;
            case "H":
                convert = "H";
                break;
            case "I":
                convert = "I";
                break;
            case "J":
                convert = "J";
                break;
            case "K":
                convert = "K";
                break;
            case "L":
                convert = "L";
                break;
            case "M":
                convert = "M";
                break;
            case "N":
                convert = "N";
                break;
            case "O":
                convert = "O";
                break;
            case "P":
                convert = "P";
                break;
            case "Q":
                convert = "Q";
                break;
            case "R":
                convert = "R";
                break;
            case "S":
                convert = "S";
                break;
            case "T":
                convert = "T";
                break;
            case "U":
                convert = "U";
                break;
            case "V":
                convert = "V";
                break;
            case "W":
                convert = "W";
                break;
            case "X":
                convert = "X";
                break;
            case "Y":
                convert = "Y";
                break;
            case "Z":
                convert = "Z";
                break;
            default:  //上面都不满足就进入这里面转换成字母
                for (int j = 0; j < str.length(); j++) {
                    char word = str.charAt(j);
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word); //转换字母
                    if (pinyinArray != null) { //如果转换的不为空也就是有这个字符对应的字母
                        convert += pinyinArray[0].charAt(0);
                    } else { //转换不过来的全部改成井号
                        convert = "#";
                    }
                }
        }
        return convert.toUpperCase(); //返回大写
    }

    public static String date() {
        Date date = new Date(); //获取当前的系统时间。
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm"); //使用了默认的格式创建了一个日期格式化对象。
        String time = dateFormat.format(date); //可以把日期转换转指定格式的字符串
        return time;
    }
}
