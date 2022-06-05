package Model.Database;

import Model.Common.DataConstants;
import Model.Database.Models.Score;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class  Database {
    // saves user to database file
    public void saveScore(Score score) throws IOException {
        String text = getScoresString() + " " + score.toString();
        sendNewData(text);
    }

    // reads user from database file
    public List<Score> loadScores() throws IOException {
        String text = getScoresString();

        try{
            return Arrays.stream(text.split(" ")).map(x -> x.split(",")).map(x -> new Score(Integer.parseInt(x[1]), x[0])).toList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    // reads user from database file
    private String getScoresString() throws IOException {
        URL url = new URL("https://api.github.com/gists/" + DataConstants.GIST_ID);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "token " + DataConstants.GITHUB_TOKEN);
        BufferedReader br = null;
        if (100 <= http.getResponseCode() && http.getResponseCode() <= 399) {
            br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
        }

        String str = br.lines().collect(Collectors.joining());;
        http.disconnect();

        Pattern r = Pattern.compile("\"content\":\"([\\w \\n\\t\\,\\\\\\.]+)\"");
        Matcher m = r.matcher(str);

        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }

    private void sendNewData(String newContent) throws IOException {
        URL url = new URL("https://api.github.com/gists/" + DataConstants.GIST_ID);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "token " + DataConstants.GITHUB_TOKEN);

        String data = "{\n  \"files\": {\n    \"data.txt\": {\n      \"content\": \"" + newContent + "\"\n    }\n  }\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}