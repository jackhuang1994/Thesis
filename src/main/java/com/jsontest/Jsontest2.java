import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Jsontest2 {
    public static void main(String[] args) {
        InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("message.json");
        InputStreamReader in = new InputStreamReader(iStream);
        JsonReader reader = new JsonReader(in);
        ArrayList<Message> list = readMessageArray(reader);
        for (Message m : list) {
            System.out.println(m);
        }
    }

    // 解析数组
    public static ArrayList<Message> readMessageArray(JsonReader reader) {
        ArrayList<Message> list = new ArrayList<>();
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                list.add(readMessage(reader));
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 解析对象
    public static Message readMessage(JsonReader reader) {
        Message message = new Message();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if ("id".equals(name)) {
                    message.setId(reader.nextLong());
                } else if ("text".equals(name)) {
                    message.setText(reader.nextString());
                } else if ("geo".equals(name) && reader.peek() != JsonToken.NULL) {
                    message.setGeo(readGeo(reader));
                } else if ("user".equals(name)) {
                    message.setUser(readUser(reader));
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // 解析Geo数组
    public static ArrayList<Double> readGeo(JsonReader reader) {
        ArrayList<Double> list = new ArrayList<>();
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                // 直接读数据就可以
                double nextDouble = reader.nextDouble();
                list.add(nextDouble);
            }
            reader.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 解析user对象
    public static User readUser(JsonReader reader) {
        User user = new User();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if ("name".equals(name)) {
                    user.setName(reader.nextString());
                } else if ("followers_count".equals(name)) {
                    user.setFollowers_count(reader.nextInt());
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}