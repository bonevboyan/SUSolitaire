package Model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {

    private ArrayList<User> userArrayList;

    public Database() {
        userArrayList = new ArrayList<>();
    }

    // adds user to our collection
    public void addUser(User user) {
        userArrayList.add(user);
    }

    // saves user to database file
    public void saveUser() {
        try{

            StringBuilder text = new StringBuilder(getUsersString());
            for (User user : userArrayList) {
                text.append(" ").append(user.getUsername()).append(",").append(user.getResult());
            }
            sendNewData(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads user from database file
    public Object[] loadUsers(File file) throws IOException {
        Object[] objects;
        String text = getUsersString();

        objects = text.split(" ");

        return objects;
    }

    private String getUsersString() throws IOException {
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
        String command = """
                curl --fail --request PATCH --header 'Content-Type: application/text' \\
                --data '{"files":{"data.txt":{
                      "content": "cock,1123 big,123321231"
                    }}}' \\
                --header 'Authorization: token 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' \\
                -- \\
                https://api.github.com/gists/f9d3cc78d23b70a7952c2bce04c0d2d9""";
        Process process = Runtime.getRuntime().exec(command);

        String json = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
