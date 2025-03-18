import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveTask;

public class ParseWeb extends RecursiveTask<String> {
    private String url;
    private StringBuffer links = new StringBuffer();
    private static CopyOnWriteArrayList<String> visitedLinks = new CopyOnWriteArrayList<>();

    public ParseWeb(String url) {
        this.url = url;
    }

    @Override
    protected String compute() {
        List<ParseWeb> taskList = new ArrayList<>();
        try {
            Thread.sleep(150);
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("a");

            for (Element element : elements) {
                String link = element.absUrl("href");

                String tab = StringUtils.repeat("\t",
                        link.lastIndexOf("/") != link.length() - 1 ? StringUtils.countMatches(link, "/") - 2
                                : StringUtils.countMatches(link, "/") - 3);

                if (link.startsWith(url) && !visitedLinks.contains(link) && !link.contains("#")) {
                    links.append(tab).append(link).append("\n");
                    visitedLinks.add(link);
                    ParseWeb task = new ParseWeb(link);
                    task.fork();
                    taskList.add(task);
                }
            }

            for (ParseWeb task : taskList) {
                links.append(task.join());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return links.toString();
    }

}
