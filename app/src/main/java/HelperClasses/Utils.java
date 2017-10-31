package HelperClasses;

import Models.UserTokens;

/**
 * Created by dejad on 2017-08-29.
 */

public class Utils {

    //Api URLS
    public static final String EmployeesAPI                = "http://dev-sidos.azurewebsites.net/api/employees";
    public static final String HorseAPI                    = "http://dev-sidos.azurewebsites.net/api/horses";
    public static final String HorseAPIGetByName           = "http://dev-sidos.azurewebsites.net/api/horses/findbyname/";
    public static final String HorseAPIGetByCharacter      = "http://dev-sidos.azurewebsites.net/api/horses/findbycharacter/";
    public static final String VetRequestAPI               = "http://dev-sidos.azurewebsites.net/api/healthreportissues";
    public static final String TokenUUserAPI               = "http://dev-sidos.azurewebsites.net/api/oauth/token";
    public static final String URLFORAPI                   = "http://dev-sidos.azurewebsites.net";
    public static final String MySecretKeyPasssword         = "1qaz@WSX3edc$RFV5tgb^YHN7ujm*IK,";

    public static UserTokens UserTokenCls = null;

    //Variables to serializable
    public static final String konSer        = "Kon";
    public static final String employeeSer   = "Pracownik";
}
