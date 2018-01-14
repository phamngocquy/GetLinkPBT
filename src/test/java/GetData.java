
import com.haku.service.JsoupGetData;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetData {

    @Test
    public void getData()
    {
        JsoupGetData jsoupGetData = new JsoupGetData();
        jsoupGetData.getServer("https://phimbathu.com/xem-phim/ky-si-ao-den-black-knight-2017-tap-2-9096_e96480.html");
}

    @Test
    public void testRegex()
    {
        String str = "dqwewewqe wqeqw ewq = \"file\":\"U2FsdGVkX18gq1G6CJ0s\\/X20Bkdn0xCNJfm+pUdO1xTygYqM6478WYruvuJItctDlU4vNaI00F+oXP91fyL7OUBb4SToRJ6gjSlI5BYjzK7Fl\\/rn2pARnBCdETZo5tA\\/hyADlwsHA1QollfBMaPV27jYO5eVL+N0o0XEwbfRCnfwa30WTV7biFcQLO1YgotKQeFmAK8kaEDtUI8l\\/ivimLCntdnP8Rpbdq00GXV6TCVqmy1R7LD+11Yj9eubNyqp0QgvWXJlZfCNXCjnbNQDvmK5Tve0KQpnmDIZpt9Nk2aqp72s63aum3+L+QPgO8Zu\"";
        System.out.println(str.matches("\\D"));
    }


    @Test
    public void test()
    {

        String stringToSearch = "https://phimbathu.com/xem-phim/bay-vien-ngoc-rong-sieu-cap-dragon-ball-super-2015-tap-123-1601_e98457.html";
        Pattern p;
        if (stringToSearch.endsWith(".html")) {
            p = Pattern.compile("-(\\d+)_e(\\d+).html");
        }else{
            p = Pattern.compile("-(\\d+)$");
        }

        Matcher m = p.matcher(stringToSearch);

        // if we find a match, get the group
        if (m.find())
        {
            System.out.println(m.groupCount());
            System.out.println(m.group(m.groupCount()-1));
        }
    }
}
