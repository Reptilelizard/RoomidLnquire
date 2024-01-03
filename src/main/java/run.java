import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;

public class run {
    public static void main(String[] args) {
        int mid = 445835400;
        String wbi = new Wbi().count(new Json().getKey(),mid);
        String url = "https://api.bilibili.com/x/space/wbi/acc/info?mid=" + mid + "&token=&platform=web&web_location=1550101";
        String Cookie = "buvid3=22181ED9-E55E-F48E-EC98-93F94257AE2D69627infoc;b_nut=1691032869;i-wanna-go-back=-1;_uuid=7A72A878-210C1-89EB-8B27-9ABFA3BDC10EB69828infoc;rpdid=0zbfVGphl5|SA0JCNri|Jz|3w1QroUq;buvid_fp_plain=undefined;LIVE_BUVID=AUTO2316910634443281;FEED_LIVE_VERSION=V_SIDE_CARD_REFRESH;header_theme_version=CLOSE;hit-new-style-dyn=1;hit-dyn-v2=1;b_ut=5;opus-goback=1;CURRENT_BLACKGAP=0;home_feed_column=5;buvid4=465719F4-6522-2112-F63E-81ACDECEA13803337-023032919-GfcDW7zVyyyKENbKAgAgLQe8p2mRsxdI1aeggtvYyl9VwBaQHx7l8w%3D%3D;enable_web_push=DISABLE;CURRENT_FNVAL=4048;SESSDATA=d0957207%2C1715095577%2C725de%2Ab2CjDpJP9FRSxZc7KARzxa_usOJfdOn7e4LpzS_3bttwPu3irWJlR4maH92-aOQXdlcuMSVjFTSUR0YVRiUU1UakpaVVBxVlNHR0owcXNPd1R1MnBSRnNRZmsxN3JjOXR2WmdDOHVna0hBSzRNNVoyZGhQdmd2UzJNeHdCaW9HMzFYeDN3MmdPZ0FBIIEC;bili_jct=a7a6fc452ce11e26439d904cec74101a;DedeUserID=445835400;DedeUserID__ckMd5=85961bbd6ced12e2;sid=7v61eeil;CURRENT_QUALITY=127;bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDQyOTU2NzksImlhdCI6MTcwNDAzNjQxOSwicGx0IjotMX0.t7aHXc3RxP1x9QBqatDVqmCvd1gPD3kF7mQBAdqtcyU;bili_ticket_expires=1704295619;browser_resolution=1476-828; PVID=2;bp_video_offset_445835400=881902161933369382;fingerprint=447559bc6129bdfd41a13e4e3efc8820;buvid_fp=60c6cd95987196cec6b46a0722dcc5e7;b_lsid=8DF86AEF_18CC93B5196";
        String finalurl = url + wbi;
        System.out.println(finalurl);
        String data = HttpRequest.get(url).cookie(Cookie).execute().body();
        System.out.println(data);
    }
}
