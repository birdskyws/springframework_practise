package mooc.birdsky.springframework.ioc.annotation.Configuration.importresource;

public class MyDriveManager {
    private String username;
    private String password;
    private String url;

    @Override
    public String toString() {
        return "MyDriveManager{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public MyDriveManager(String username, String password, String url){
        this.username = username;
        this.password = password;
        this.url = url;
    }
}
