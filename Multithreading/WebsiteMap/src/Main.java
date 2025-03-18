import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ParseWeb parseWeb = new ParseWeb("https://sendel.ru/");
        String pathWebsiteMap = "./WebsiteMap/data/WebsiteMap.txt";

        String links = new ForkJoinPool().invoke(parseWeb);
        writer(links, pathWebsiteMap);
    }

    public static void writer(String links, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(links);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}