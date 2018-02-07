package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dejad on 2017-10-23.
 */

public class UserTokens implements Serializable{

    private String access_token;
    private String token_type;
    private String userName;
    private String EncryptedPass;
    private String roles;
    private ArrayList<String> UserRolesList;


    public UserTokens(String access_token, String token_type, String userName,String EncryptedPass,String roles) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.userName = userName;
        this.EncryptedPass = EncryptedPass;
        this.roles = roles;
        UserRolesList = getRolesFromString();
    }

    public String getEncryptedPass() {
        return EncryptedPass;
    }

    public void setEncryptedPass(String encryptedPass) {
        EncryptedPass = encryptedPass;
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

    public String getroles() {
        return roles;
    }

    public void setRole(String roles) {
        this.roles = roles;
    }


    public ArrayList<String> getUserRolesList() {
        return UserRolesList;
    }

    public void setUserRolesList(ArrayList<String> userRolesList) {
        UserRolesList = userRolesList;
    }

    private ArrayList<String> getRolesFromString(){
        ArrayList<String> arr = new ArrayList<String>();

        if(roles != null){
            for(String st : roles.split(";")){
                arr.add(st);
            }
        }
        return arr;
    }

    public void UpdateListRoles(){
        UserRolesList = getRolesFromString();
    }
}
