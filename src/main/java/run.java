public class run {
    public static void main(String[] args) {
        String cookie = "Hm_lvt_6ab26a3edfb92b96f655b43a89b9ca70=1699535386,1701873879; Hm_lvt_a69e400ba5d439df060bf330cd092c0d=1699535386,1701873879; buvid3=C20B1FC6-AECB-2316-2A96-218BBE8551E185296infoc; b_nut=1704302985; buvid4=653888BF-B487-D9E2-EFF8-C820C7A280FD85296-024010317-mZBso5NfXMxizoivDIBD7w%3D%3D; _uuid=3D5941071-A10DE-1AB3-4697-9827E44EBB4387769infoc; enable_web_push=DISABLE; home_feed_column=5; browser_resolution=1460-820; header_theme_version=CLOSE; buvid_fp=67a83aa7752ddf43a142ea143e43fde4; SESSDATA=16d7c7ed%2C1719855002%2C0f161%2A12CjAHwcELc4PJx5PHru7KxKrooSzPHYriN6Zr_tSDAx4IumvK2Mbx9G70EnXhcxlhZ5cSVjlQaWVJRUlJS0hzWTdRRXBhS0dMNmFEOEE0eE9wb3JBcDhjak9UTG9jTHdGNnVZZVhDTzVNV051SDF0TzAtWmZlUUIybURIZFRDcFFPdjU0X1YtMFlRIIEC; bili_jct=259a6c1ca8377c389eb12777717653be; DedeUserID=445835400; DedeUserID__ckMd5=85961bbd6ced12e2; CURRENT_FNVAL=4048; rpdid=|(um|||muum|0J'u~|RJ|JkJm; CURRENT_QUALITY=80; PVID=1; LIVE_BUVID=AUTO5117043513419627; b_lsid=1041086463_18CD341A5F6; sid=8ghwxbll; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDQ2MTE0OTIsImlhdCI6MTcwNDM1MjIzMiwicGx0IjotMX0._5QloX3wHppF5nb0YcuSJ1W8lv3lYmhC-b-YjyyS6BI; bili_ticket_expires=1704611432";
        String access_key = "610fdb6c24f1648a72b07bdec0027bc2";
        Web web = new Web();
        String[] roomId = web.getRoomId(web.getGroupUser(access_key,422067),cookie);
        for (String id:roomId){
            System.out.print(id + " ");
        }
    }
}
