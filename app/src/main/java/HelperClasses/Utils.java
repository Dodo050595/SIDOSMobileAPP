package HelperClasses;

import java.util.ArrayList;
import java.util.List;

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
    public static final String TaskAPI                     = "http://dev-sidos.azurewebsites.net/api/Tasks";
    public static final String TaskAPIChangeStatus         = "http://dev-sidos.azurewebsites.net/api/tasks/changestatus";
    public static final String EventAPI                    = "http://dev-sidos.azurewebsites.net/api/events";
    public static final String EventApigetByDate           = "http://dev-sidos.azurewebsites.net/api/events?date="; //dd-MM-yyyy
    public static final String TokenUUserAPI               = "http://dev-sidos.azurewebsites.net/api/oauth/token";
    public static final String URLFORAPI                   = "http://dev-sidos.azurewebsites.net";
    public static final ArrayList<String> CanAddVetRequest = new ArrayList<String>() {{
        add("Supervisor");
        add("Vet");
        add("Instructor");
        add("Blacksmith");
        add("Groom");
    }};

    public static final ArrayList<String> CanSeeTasks = new ArrayList<String>() {{
        add("Supervisor");
        add("Instructor");
        add("Blacksmith");
        add("Groom");
    }};

    public static final ArrayList<String> CanReadVetRequest = new ArrayList<String>() {{
        add("Supervisor");
        add("Vet");
    }};


    public static final String MySecretKeyPasssword        = "dJjtyWG9HB1VOVDX65tS" +
            "YcyYkXvYIH8Q5hHiokQY" +
            "LEigfj2cGR4ElJqhJiT6" +
            "6IebwK5fbRaPRzpwNQYd" +
            "p7Thzp1PKUzmraw21w63" +
            "Ql1w44ATXJT3eyOx2qqW" +
            "1n7CNDLx6GuXgzEaoPFo" +
            "qy6Amhyudz0KNE07sEuX" +
            "pMaOOui7T5JgXN7jqrgO" +
            "utsWS4wE2fhuUO6nxlvJ" +
            "RzkPXlbo0AmKZVWlOvYu" +
            "nz2IGp1ghCs1spSryxQT" +
            "KjVvT3whCrO7zZL3pMlW" +
            "IgYTbkyIfQoHEtJMVPxv" +
            "kM5yncxmKmWUYYc0B54B" +
            "SXT90XodvHiomLwDgtCj" +
            "WslSktxI4WfGlFK0JaYK" +
            "c6wMRYJhlprN3RgBNstm" +
            "utSdJCncQqmuTxhGBBlD" +
            "k9Cu1EV8HNBK6sZDSs4p" +
            "EXvyRPYbdeQJG7J1eksa" +
            "YfJMDmtOyxPKYWnuAuUu" +
            "wiJkv6t6NKcwwpGYGo3D" +
            "fxWQF3B0Ln2HLcjtYoPg" +
            "5rpXX2JW0DZslyfRc4ae" +
            "AeUyull17bm2m8qfpDXe" +
            "RcGXy6grRye2lMAwsZoa" +
            "W7bAHmN9jMlZLslDUJsO" +
            "vjDfrBLERymsxlnHFW5A" +
            "JOs9o51mXaGZF1EMeUa7" +
            "2NMnP8Sl9kXRUeCEXToN" +
            "WW43WfbI4ueufuLLRGbf" +
            "4OQClTjgitFKAI4AUqSq" +
            "otnzextkpndNWj3du2NN" +
            "JCCQRFO2Kb7QBkhB4MHB" +
            "6JSWamKgWstgMuFuNjBW" +
            "Ujxy6qrAIWMfiuLnKwHP" +
            "fTYe7iGGoLw2UnOxcsVO" +
            "vUWHEpc0Ipi0Ax3Y6MDN" +
            "lyByfJAbynSIQSnqPWTI" +
            "qihmcLciCCTGDoE9BkVU" +
            "AyfwiKOqmlMevxeRo4DF" +
            "f92scULkC0nEvYUgUOnL" +
            "FBhDKe6Qw3X6UMpcxWEo" +
            "HlADjjUlFyVAAk1pL0Xg" +
            "WwzTfGsOQD0AiVsHmgi8" +
            "eJgY6ok9VDK6xyRpNAHc" +
            "c579EmSHU4xRtQ6ZBAUL" +
            "pKceFuPqVTdkBXpRJVWp" +
            "MqcXnB66GyeEIh5xPXqz" +
            "vQOWHQpTwUmIU9Dqp2DO" +
            "6MdQk8oRlZPWvU4mtO6B" +
            "UVYMnlzrsNWOsmGyeZeB" +
            "aNWSs0XJVqJ7xY5ttL15" +
            "fGi9usyh3zcNCnWkpHTm" +
            "lyeK8HiDYSeCbba35yqD" +
            "hd6RlqOojPFX4A6W0luA" +
            "J1Vr0F7Xp0yJZ53WS6cq" +
            "DBAGvV2hVTgWRcLKIZ0t" +
            "aaFFSHhZo9lNQw5N4Wch" +
            "IlBp6pbZwrfQbu7vWCDM" +
            "28Kq4R7pGDfIqhd6NlaV" +
            "WUeMktdEB4nDOXzAXVmZ" +
            "b22CgLkMqSXXZcS9jMRq" +
            "tCeoeEk10WRhNE0SGWil" +
            "KQdlOyOrA9QKFhTLGzsk" +
            "PLpCaqiVZtTvPhqMTGLf" +
            "LvpUZeUxfZasABVyRmCw" +
            "rhFnChIhMkc4lLQljRAy" +
            "CtRbSLLc9nVK6nYGFil2" +
            "wmzWmzogQQiIIYSjrbm6" +
            "hR76AcgQLq0qEzHZiHgB" +
            "hBYpTQJQ2625aMRaMZOL" +
            "NkiQucrFJefOS2THOWdG" +
            "VHrYMYOdG0zG7dv0QbkJ" +
            "a9lSkndqyoavdYdCBpJW" +
            "BTyt806s3jIVTQIlemIQ" +
            "0M4MJS93LgdAylpDQZy9" +
            "R0QgXjlxIkkNAXqB7E68" +
            "1E6fF4vy9imEA3Uu2atR" +
            "hLlEdGCuMhw9cOEBcQTS" +
            "4h3zGuSk1U2eKmKROft5" +
            "m1DepZ0ri06wdNMqttRm" +
            "qlWpXkeMnsNFPkv7RTox" +
            "FLCDxAhYoZdNwQgwciDh" +
            "Z8Zbh1kkN1BSJTFrtZSf" +
            "eIX3NnQ8dCczSzWpbxTW" +
            "DQ3gmvCwE0DaQ6oBtsbW" +
            "QmpbsIeuywm7F4lXaA0g" +
            "v2ElUhCxI322BWhxGu43" +
            "1CSVDbJoQzKppqqnHF1G" +
            "UMtEd0rwkuuMXPOS7GXF" +
            "DZCN758RcxnhoChgI7bH" +
            "9cl87U5hf32KJziobYiL" +
            "6we7rdNE8sziDO7z4Wgd" +
            "DRuJ1uHsx4hrAbEG9a5V" +
            "vzDFdlrhh8MsEpgERS9V" +
            "ZyedZXkbvN6CdtpQ4SMU" +
            "pZ7ZLsWa9nxaVyG87CUB" +
            "6J2jMankMbFmgpFOm5yL" +
            "JMA5x67eckDxInImpbAf" +
            "0FcXCRc8y4J8Uh4Il0LA" +
            "Ou83NHMGNUR0uEebuHFB" +
            "GpkN8JWpouECk8vDrJty" +
            "WlOQPVhE4oQKDu8jpR9O" +
            "yeL5e8SaNp7X7xXPldhb" +
            "J7B7zv5Nrfj5CueEGle2" +
            "P2s0HgG3BSxxEgxizFU6" +
            "Xvi2X6vuuzEaRcabxzhK" +
            "ehni3kZkwZdQ8OJorcgO" +
            "vsfn6sq7j5Nd1P3zurQe" +
            "1MUDRRcCWfGcxtSRYtkB" +
            "V91DySxwwpO1gL89En0n" +
            "V2QrNknmvE6QDHt6z5i6" +
            "189IHvvIsj3fqlXd4cxK" +
            "MDEH3TkFVlp2xJrjnQHi" +
            "t0zmeTq8GeNqlzuhJAT6" +
            "vL058hes5ddisJkzyp5v" +
            "nLVb9JMZieaHG2w7cJe7" +
            "Ine4Nakrr0gxWuTmQH6M" +
            "9chHSXjS2Fce80wu9raF" +
            "9Goc92TvPrTdhIAYulgM" +
            "YM43u7Y2P7k3SXAxQoRP" +
            "diMIgG45g2uX1b3CIOKe" +
            "QeCwbXkEa7EgHHPGjIpi" +
            "hDG1oAS9raDxaUVprxF7" +
            "5YqyVmlW66LM2ttX2Y0p" +
            "rsWEI4XWZjjIzr2ti3M4" +
            "9Z4RJbhaBHNcY8q6OoXp" +
            "OQn2bzl9lopxhxMLj9ZJ" +
            "ur3YaEvazVDdzKR5vXkD" +
            "rdE1pfiMONQvZz3Eblj3" +
            "CeynChkFCmYBjMQG3hsA" +
            "yFnSnzf85YdgisxHT7ii" +
            "l6BzE47cMlCsgNflSwD4" +
            "SeNIyCbWZ9VJ3qXVDdDn" +
            "a0kLvxL0U13dJvYUvprS" +
            "jRBHHr344oyFSumGMY2i" +
            "vobyFoqaKXhfFTSHT88J" +
            "sDkTVh6fFWyWOfm616gN" +
            "wlIdMYTTJ4UtYQoNpt1E" +
            "sRU9OIyaRIClEsA0UUNZ" +
            "E27Mi9AgL95MTvUKEhCW" +
            "nYdBproqEq7llpLIpcv0" +
            "tEfAgPBZXgWe6UfPvMHA" +
            "XYjYBjnaxsjY3zLrafbJ" +
            "iybnoJzGoiYnXJEl6cYz" +
            "WoXGbIkfKmjc2iKqSY0R" +
            "hBb1UylmPaiYnwzllozf" +
            "0iPvZMFptReqrWKO0ZzQ" +
            "kSrLDrCylI7NmwUTfxoU" +
            "jByGZSedwMRj0SoW3HGC" +
            "SY6ZP2qOG4c4MHSh9XyN" +
            "AtJ81bLDbIsXGnbLjePe" +
            "sq5AqkLutH21sO8jOh48" +
            "1cJ1SFu5m7ZqUDRCSydJ" +
            "5Uo4FbGUiQiX9jWoidht" +
            "8sEF600A1f1hkedegX4t" +
            "t48DUf56PtKYyPWwjs0Q" +
            "vGYVucPDkQHfIyunqD17" +
            "LklINdgvSbBg7e8RfUKe" +
            "aaYY0G56UJUlDcDTvi1G" +
            "AGHQNlBjfmNkAw48QWHw" +
            "v84K2hI662UgFMjL4Yp7" +
            "Jue3J88xLc8ZPnu2eEL3" +
            "rQrg0a3iKDEbeV17HYMp" +
            "WYJX7OhLuBoCqqN92m5l" +
            "ealC4PZqWzu07jt4ghFb" +
            "h7HyjVSMZOdlTevtbOs7" +
            "zr569r4mOhfn1ssu1yAY" +
            "YI920Nq1ItvZFmDZmA3s" +
            "86Rdf5iiXSssQCWQrcsI" +
            "yYHNgUVGvLADrVbUtxnA" +
            "mTu7yqnlBy1Z9KkPmxf7" +
            "G1H1NOKM1ZPOu4UGQfmd" +
            "sbx9pdvNQQuA9KqSxahx" +
            "H83eItFIdOP7r1PcSnhM" +
            "w8SqLKWGs2mHscrTX06h" +
            "CUR1sa7mVCLJkSRIRe17" +
            "dkJzGMCzHzJKB6oiAaVF" +
            "6c2Qk3IHuwmKn16hnt23" +
            "KI7G86BXGiC66E6BMGz3" +
            "0x0NB4H1R6Upv2fCCq8T" +
            "tVsEYvA06x0KT31CW52g" +
            "dgHbrDqLXen0ehzXzJBP" +
            "sqoiBUYLEpdfU8eMxO74" +
            "xGjFDwgwefFxoCWlBGyb" +
            "NC4i5UZFRwSWroqdkuxN" +
            "G7we66XgvKG6eyxS8KW4" +
            "kF6w0hM4jbyJ659xDNiS" +
            "nEyK5I6bViaJS9qYK6ag" +
            "tgGLSLRGio8ElgqjOt7W" +
            "X4tSX1xKX3a0KQcJvnl2" +
            "67XWbFe0nZpEpRwJd1yd" +
            "lCeLxrxxfF4VfY3um6S3" +
            "gfWe25nEjxlyqwLYy3pa" +
            "1ZQ7LejTX5HBhjE9eikI" +
            "4e8JTLhUXCYgKb3jZMgv" +
            "aWDtSw3q2LQqjNGJ2gvY" +
            "mthXFNt2lkjFOre5kRjf" +
            "ZOlnaUB1Nd60oTDAIDxe" +
            "QTIXLrfS2yGUwcYmY256" +
            "NUoEGOUSp8JFl5KSh3QB" +
            "tT0ycOiZf55QzM0Uls4W" +
            "cEsfAmOnm5Ti778TQBIy" +
            "WgxqRWM2Ooda3GqplfNW" +
            "bHEEutDKkg1DMSBbPkVj" +
            "iKI6g7Luc3OSr6cYmbIO" +
            "zUBAOQD5d96FFE8AVDpz" +
            "FQUwDcbjAEa4tRF7yjAa" +
            "CDyeiIsboUmLMhZ4mStv" +
            "NRvTf9u1488HmNE5NBiD" +
            "i6NQkjYyLp4znUvefy5f" +
            "Y6fEfi0o4py0Q7f89KJe" +
            "Bv31lDJYzljL4EV022fe" +
            "xna6m8BBfay3iTjcDcKu" +
            "C7hk6B2XZ456w5ClBcV8" +
            "49v1Bkr0Zguq1VBV2jVe" +
            "XWVyQFLOsvSRfcWcNR1V" +
            "IeIvgL3ap1RDdm2ay5LC" +
            "6SNmHAJu1GBTivKL8GDI" +
            "Ksnrh3sSd1lkXw5mxekj" +
            "yhiJsYml6XqcZ97M9DeG" +
            "SMVoeFF0COLQmnoMS1uQ" +
            "2MNe4Zs78csMSnFbU2Cj" +
            "tF43CxsIqup42D9SEjJx" +
            "BTCanM70voXnzxFQZKb6" +
            "Mb2vYcmeF2BidFr33xQy" +
            "bFl68BCU0Equ3jdEz8tj" +
            "PktcbarbQvq2ISlJ5OCj" +
            "nyhm7gP27iLOPMToThHk" +
            "1ljNumMHDqwHBQOJR157" +
            "YyC7JeyYhGK5CsCh4Zwm" +
            "FBzRMWZoODM0xc1Pq8Rz" +
            "WCHoKclrACWoC9EhvKM2" +
            "fVkVklYQ9KzWPrVBbiu3" +
            "KUXwXMqWB9boZtyTsjAZ" +
            "4tvJJZvpkdi9taxHrmNq" +
            "K3skVMwLphJfzmW1juke" +
            "CBnsRnG4qBx1eJglzEr9" +
            "WL5fbfu3qkIllMbSIdLR" +
            "t1ex5oApg1NDzagYNyCH" +
            "9U2lA48UZ76CE7rZtgxA" +
            "866AEwDoHw6FnJL2eqVq" +
            "6Qi5llyqbrIcxL0tqAls" +
            "vHyl2hO9aRJdqRcKjXYW" +
            "lu3bnwl6fmgnBC2vBE6q" +
            "vHbvsnsTBXuVusUPmp7S" +
            "awlCM0nHIUD9xy2TpXo4" +
            "eBp7YLFEkrwcDxOsuJSH" +
            "tNsl7NPPMs2jh4waLMTa" +
            "Fj6XdgGIPQ7caRrdyvOJ" +
            "ORIk9rvfQCOSMbgLSPIB" +
            "FQY1pQggyPsSByUaOsss" +
            "5N7pShk6S3Eq1xZmJc2k";

    public static UserTokens UserTokenCls = null;

    //Variables to serializable
    public static final String konSer           = "Horse";
    public static final String employeeSer      = "Employee";
    public static final String vetRequestStat   = "Vet_Request";

    public static List<String> generateHoursOfLessons(){
        List<String> stList = new ArrayList<String>();
        stList.add("10-11");
        stList.add("11-12");
        stList.add("12-13");
        stList.add("13-14");
        stList.add("14-15");
        stList.add("15-16");
        stList.add("16-17");
        stList.add("17-18");
        stList.add("18-19");
        stList.add("19-20");
        stList.add("20-21");
        stList.add("21-22");

        return stList;

    }
}
