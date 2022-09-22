import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        int max = 20000;
        boolean init = false;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("C:\\Users\\shcrl\\Downloads\\output.txt")), StandardCharsets.UTF_8));
        BufferedReader br = FunctionUtil.getAnsiFile("C:\\Users\\shcrl\\Downloads\\seoul.csv");

        String line;
        int idx = 0;
        while((line = br.readLine()) != null){
            idx++;
            if(idx == 1)
                continue;

            String[] lineArr = line.split(",");

            for(int i = 0; i < lineArr.length; i++){
                String temp = lineArr[i];
                lineArr[i] = temp.replaceAll("\"", "");
            }
            if(lineArr[7].equals("영업")){
                String name, marketType, startDate, address, zipCode;

                startDate = lineArr[2];
                zipCode = lineArr[14];
                address = lineArr[15];
                name = lineArr[18];
                marketType = lineArr[22];
                try{
                    createRestaurantSqlFile(startDate, zipCode, address, name, marketType, bw, init);
                    init = true;
                }catch (Exception ignored){

                }
                if(idx > max)
                    break;
            }
        }
        br.close();
        bw.close();
    }

    public static Optional<Position> findLocationByAddress(String address) {
        Position position = null;
        String kakaoApiKey = "8752754b20b6e82bf8a93fbe066ac3a3";

        String url = "https://dapi.kakao.com/v2/local/search/address.json";

        BufferedReader in = null;
        try{
            url += "?query="+ URLEncoder.encode(address, "UTF-8");

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "KakaoAK " + kakaoApiKey);

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jObject = new JSONObject(sb.toString());

            JSONObject addressObj = jObject.getJSONArray("documents").getJSONObject(0).getJSONObject("address");

            position = new Position(
                    addressObj.getDouble("y"),
                    addressObj.getDouble("x"),
                    addressObj.getString("region_1depth_name"),
                    addressObj.getString("region_2depth_name")
                    );

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }
        return Optional.ofNullable(position);
    }

    public static void createRestaurantSqlFile(String startDate, String zipCode, String address, String name, String marketType, BufferedWriter bw, boolean init) throws Exception {
        Optional<Position> optionalPosition = findLocationByAddress(address);

        Position position = optionalPosition.orElseThrow(() -> new Exception("non"));
        String result;
        if(!init){
            result = "INSERT INTO restaurant (city, smallCity, name, marketType, isOperating, startDate, latitude, longitude, address, zipCode) VALUES \n (" +
                    "'" + position.getCity() + "'" + "," +
                    "'" + position.getCity2() + "'" + "," +
                    "'" + name.replaceAll("'","\\'") + "'" +
                    "," +
                    "'" + marketType + "'" +
                    "," +
                    "true" +
                    "," +
                    "'" + startDate + "'" +
                    "," +
                    position.getLatitude() +
                    "," +
                    position.getLongitude() +
                    "," +
                    "'" + address + "'" +
                    "," +
                    "'" + zipCode + "'" +
                    "), \n";
        }
        else {
            result = "(" +
                    "'" + position.getCity() + "'" + "," +
                    "'" + position.getCity2() + "'" + "," +
                    "'" + name + "'" +
                    "," +
                    "'" + marketType + "'" +
                    "," +
                    "true" +
                    "," +
                    "'" + startDate + "'" +
                    "," +
                    position.getLatitude() +
                    "," +
                    position.getLongitude() +
                    "," +
                    "'" + address + "'" +
                    "," +
                    "'" + zipCode + "'" +
                    "), \n";
        }
        bw.write(result);
    }
}