public class Position {
    public Position(double latitude, double longitude, String city, String city2){
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.city2 = city2;
    }
    private final double latitude;
    private final double longitude;

    private final String city;
    private final String city2;

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public String getCity(){
        return city;
    }

    public String getCity2(){
        return city2;
    }
}