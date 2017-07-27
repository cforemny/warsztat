package com.mkyong.sqlBase;

import org.springframework.stereotype.Component;

/**
 * Created by Cyprian on 2017-07-07.
 */
@Component
public class CurrentUrlCutter {

    private final String REGEX = "/";

    public String getTableNameFromUrl(String urlAdress) {

        String[] cuttedUrl = urlAdress.split(REGEX);
        String tableName = cuttedUrl[cuttedUrl.length - 1];
        return tableName.toLowerCase();
    }
}
