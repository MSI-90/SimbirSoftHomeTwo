package helpers;

import org.hamcrest.text.IsEmptyString;

public class GetAllFilter {
    public String title;
    public boolean verified;
    public int page;
    public int perPage;

    public GetAllFilter(String title, boolean verified, int page, int perPage){
        this.title = title;
        this.verified = verified;
        this.page = page;
        this.perPage = perPage;
    }

    protected String getParams (String path){
        String parametrisetString = "";

        if (!title.isEmpty()){
            parametrisetString += title;
        }


        return parametrisetString;
    }

}
