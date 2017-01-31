/**
 * Created by adrianmaryniewski on 30.01.2017.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        GettingValuesFromTheTable runner = new GettingValuesFromTheTable();
        runner.getValuesFromTable();
    }
}