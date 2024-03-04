package helpers;

import org.hamcrest.text.IsEmptyString;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static java.net.URLDecoder.decode;

public class GetAllFilter {
    public String title;
    public boolean verified;
    public int page;
    public int perPage;

    /*
    public GetAllFilter(String title, boolean verified, int page, int perPage){
        this.title = title;
        this.verified = verified;
        this.page = page;
        this.perPage = perPage;
    }
    */
    public String getParams () throws UnsupportedEncodingException {
        String parametrisetString = "";

        if (!title.isEmpty()) {
            parametrisetString += "title=" + title;
        }

        if (verified) {
            if (!parametrisetString.isEmpty()) {
                parametrisetString += "&";
            }
            parametrisetString += "verified=" + verified;
        }

        if (page > 0) {
            if (!parametrisetString.isEmpty()) {
                parametrisetString += "&";
            }
            parametrisetString += "page=" + page;
        }

        if (perPage > 0) {
            if (!parametrisetString.isEmpty()) {
                parametrisetString += "&";
            }
            parametrisetString += "perPage=" + perPage;
        }

        String decodedString = URLDecoder.decode(parametrisetString, "UTF-8");

        return decodedString;
    }

}
