package com.wj.spring.mvc.rest.test.util;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author :  wangjia_yql@qq.com
 */
public class EgTestUtils {

    public static void runSqlScripts(WebApplicationContext wac, JdbcTemplate jdbcTemplate, String... sqlScriptPaths) {
        for (String sqlScriptPath : sqlScriptPaths) {
            Resource resource = wac.getResource(sqlScriptPath);
            JdbcTestUtils.executeSqlScript(jdbcTemplate, resource, true);
        }
    }

}
