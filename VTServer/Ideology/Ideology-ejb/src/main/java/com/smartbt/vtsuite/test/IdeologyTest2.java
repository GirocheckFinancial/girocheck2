package com.smartbt.vtsuite.test;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.manager.IdeologyBusinessLogic;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class IdeologyTest2 {

    private static final DateFormat df = new SimpleDateFormat("MM/dd/YYYY");

    private static IdeologyTest2 INSTANCE;

    public static synchronized IdeologyTest2 get() {
        if (INSTANCE == null) {
            INSTANCE = new IdeologyTest2();
        }
        return INSTANCE;
    }

    private static String successTestCases
            = "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,	ATLANTA ,	GA 	,30318 	,Feb-05, 	3333 \n"
            + "JOHN ,	SMITH ,	2240 MAGNOLIA ,	ATLANTA ,	GA 	,30318 	, , 	3333 \n"
            + "JOHN ,	SMITH ,	222333  MAGNOLIA ,	ATLANTA ,	GA 	,30318 	, , 	3333 \n"
            + "JOHN ,	SMITH ,	2240  PEACHTREE PLACE ,ATLANTA ,	GA 	,30318 	,   , 	3333 \n"
            + "JOHN ,	SMITH ,PO BOX 123  ,ATLANTA ,	GA 	,30318 	, , 	3333 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,	ATLANTA ,	GA 	,30318 	, 1975 , 	3333 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,ATLANTA ,	GA 	,30318 	, Feb-70, 	3333 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,ATLANTA ,	GA 	,30318 	, Feb-76, 	3333 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,ATLANTA ,	GA 	,30318 	, May-75, 	3333 \n"
            + "JOHN ,	SMITH ,	3612 ANY ST  ,ATLANTA ,	GA 	,30318 	, , 	3333 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,ATLANTA ,	GA 	,30318 	, , 	3345 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,ATLANTA ,	GA 	,30318 	, , 	3334 \n"
            + "JOHN ,	SMITH ,	222333 PEACHTREE PLACE ,	ATLANTA ,AL,30318 	, , 	3333 \n"
            + "JANE ,	SMITH ,	5432 ANY PLACE, LA CRESCENTA,	GA 	,91214 	, 1975, 	1111 \n"
            + "JOHN ,	BLACK ,	345 SOME AVE ,	ATLANTA ,	GA 	,30303 	, , 	  \n"
            + "JANE ,	BROWN ,9000 ANY ST , LA CRESCENTA ,	CA 	,91224 	, , 	1010 \n"
            + "JANE ,	BLACK,	12345 MAGNOLIA WAY,	ATLANTA ,	GA 	,30303 	, Feb-75, 	  \n"
            + "JAMES ,	WILLIAMS ,	5555 MOUNTAIN ROAD ,	ROCKMARKET ,	GA 	,30153 	, ,  \n"
            + "WILLIAM ,JAMES ,	5555 BEACH  AVENUE ,ROCKMARKET,	GA ,30153 	, , 6789\n"
            + "WILLIAM ,JAMES,	5555 BEACH  AVENUE , ROCKMARKET,	GA 	,30153 	, , 6789 \n"
            + "JANE ,	WILLIAMS ,	8888 ANY STREET , DALLAS,	GA 	,30132 	, 1975 , 	3333";

    private static String[][] successRecords;

    static {
        String[] rawRecords = successTestCases.split("\n");

        successRecords = new String[rawRecords.length][8];

        for (int i = 0; i < rawRecords.length; i++) {
            successRecords[i] = rawRecords[i].split(",");
        }
    }

    public Map<ParameterName, Object> buildMap(String[] raw) throws ParseException {
        Map<ParameterName, Object> params = new HashMap<>();

        params.put(ParameterName.FIRST_NAME, raw[0]);
        params.put(ParameterName.LAST_NAME, raw[1]);
        params.put(ParameterName.ADDRESS, raw[2]);
        params.put(ParameterName.CITY, raw[3]);
        params.put(ParameterName.STATE_ABBREVIATION, raw[4]);
        params.put(ParameterName.ZIPCODE, raw[5]);
        params.put(ParameterName.BORNDATE, raw[6]);
        params.put(ParameterName.SSN, "11222" + raw[7].trim());

        return params;
    }

    public String sendSuccessTest(int j) throws Exception {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < successRecords.length; i++) {
            sb.append(IdeologyBusinessLogic.get().verifyClient(buildMap(successRecords[i])));
            sb.append("                ");
            sb.append("                ");
            sb.append("                ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] rawRecords = successTestCases.split("\n");

        successRecords = new String[rawRecords.length][8];

        for (int i = 0; i < rawRecords.length; i++) {
            System.out.println("" + rawRecords[i].split(",").length);
        }
    }
}
