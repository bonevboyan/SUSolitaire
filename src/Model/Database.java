package Model;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    // saves user to database file
    public void saveScore(Score score) throws IOException {
        String text = getScoresString() + " " + score.toString();
        sendNewData(text);
    }

    // reads user from database file
    public List<Score> loadScores() throws IOException {
        String text = getScoresString();

        return Arrays.stream(text.split(" ")).map(x -> x.split(",")).map(x -> new Score(Integer.parseInt(x[1]), x[0])).toList();
    }

    // reads user from database file
    private String getScoresString() throws IOException {
        String command = "curl \\\n" +
                "  -H \"Accept: application/vnd.github.v3+json\" \\\n" +
                "  https://api.github.com/gists/f9d3cc78d23b70a7952c2bce04c0d2d9\n";
        Process process = Runtime.getRuntime().exec(command);
        String json = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        Pattern r = Pattern.compile("\"content\": \"([\\w \\n\\t\\,\\\\]+)\"");
        Matcher m = r.matcher(json);

        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }

    private void sendNewData(String newContent) throws IOException {
        URL url = new URL("https://api.github.com/gists/f9d3cc78d23b70a7952c2bce04c0d2d9");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "token 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");

        String data = "{\n  \"files\": {\n    \"data.txt\": {\n      \"content\": \"" + newContent + "\"\n    }\n  }\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        http.disconnect();
    }
}
