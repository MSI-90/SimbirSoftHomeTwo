package constants;

import static constants.Constants.Path.testApiPath;
import static constants.Constants.Servers.testApiUrl;

public class Constants {

    public static class RunVeriable {
        public static String server = testApiUrl;
        public static String path = testApiPath;
    }

    public static class Servers {
        public static String testApiUrl = "http://localhost:8080/";
    }

    public static class Path {
        public static String testApiPath = "api/";
    }

    public static class Actions {
        public static String create = "create";
        public static String getAll = "getAll";
        public static String getId = "get/";
        public static String delete = "delete/";
        public static String patch = "patch/";
    }
}
