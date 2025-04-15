package models;

import lombok.Data;

@Data
public class UserTestsGetInfoResponseModel {


    private Data data;
    private Support support;


    @lombok.Data
    public static class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }
}



