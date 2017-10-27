package Models;

import java.io.Serializable;

/**
 * Created by dejad on 2017-10-23.
 */

public class UserTokens implements Serializable{

    private String access_token;
    private String token_type;
    private String userName;


    public UserTokens(String access_token, String token_type, String userName) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.userName = userName;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
