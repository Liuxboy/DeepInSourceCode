import org.apache.logging.log4j.LogManager;

/**
 * Package: PACKAGE_NAME <br>
 * Author: liuchundong <br>
 * Date: 2017/9/1 <br>
 * Time: 15:01 <br>
 * Desc: 直接通过log4j2.xml向kafka发送日志
 */
public class KafkaLogTest {

    public static void main(String[] args) {
        LogManager.getLogger(KafkaLogTest.class).info("Hello log4j!!!");
    }
}
