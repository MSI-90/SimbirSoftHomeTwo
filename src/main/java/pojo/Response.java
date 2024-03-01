package pojo;

import java.util.ArrayList;

public class Response {
    public int id;
    public String title;
    public boolean verified;
    public Addition addition;
    public ArrayList<Integer> important_numbers;

    public class Addition{
        public int id;
        public String additional_info;
        public int additional_number;
    }
}
