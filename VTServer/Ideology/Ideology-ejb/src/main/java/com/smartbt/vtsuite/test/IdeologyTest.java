package com.smartbt.vtsuite.test;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.entity.IdeologyResponse;
import com.smartbt.vtsuite.manager.IdeologyBusinessLogic;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class IdeologyTest {

    private static final DateFormat df = new SimpleDateFormat("MM/dd/YYYY");

    private static IdeologyTest INSTANCE;

    public static synchronized IdeologyTest get() {
        if (INSTANCE == null) {
            INSTANCE = new IdeologyTest();
        }
        return INSTANCE;
    }

    private static String successTestCases
            = "Johnny,	Brown,	565 Main Street	,Atlanta, 	GA,	30339,	4/10/1969,	223334444\n"
            + "Clara,	Dixon,	111 Green Bay Avenue,	Chatham,	        IL ,	65591,	2/11/1974,	334445555\n"
            + "Emily, 	Carter,	9156 Rock Circle,	Chicago , 	IL ,	65531,	1/12/1958,	111345117\n"
            + "Ben 	,Sampson,	124 Brown Ridge Drive,	New York,	NY,	11154,	8/27/1977,	114455667\n"
            + "Charles,	King	,61 High Mountain Ave,	Denver,	CO,	87124,	5/1/1973,	556667777\n"
            + "Ryan,	Bennett	,123 Robin Ridge ,	Atlanta ,	GA	,30339,	1/12/1984,	221113333\n"
            + "Gary ,	Kline	,54 Sycamore Lane,	Chicago ,	IL ,	65519,	7/8/1981,	445556666\n"
            + "Rosa	,Hernandez,	951 Green Tree Lane,	Denver,	CO,	87124	,9/5/1974	,777889999\n"
            + "Crystal,	Sparks	,12 East Main Street,	Atlanta	, GA	,30339,	3/6/1967,	255667777\n"
            + "Steve	,Harris,	27 HWY 9816	,Raleigh,	NC	,24012,	1/1/1991,	122334444\n"
            + "James	,Stone,	145 Hawthorne Lane,	Denver,	CO,	87124,	2/17/1964,	667778888\n"
            + "Corey	,Smith	,73198 Circle Street	,San Francisco	,CA	,91119	,4/12/1990	,123994456\n"
            + "Henry,	Warren,	171 Sycamore Street,	Phoenix ,	AZ,	77123,	3/28/1988,	123654789\n"
            + "Ruth, Anne,	Pitcher	9 E Clairborne Ave,	Phoenix ,	AZ,	77123,	11/10/1972,	279341234\n"
            + "Terry,	Robinson	,19 Greenwoods Circle	,Denver,	 CO	,87124	,1/30/1976,	398012345\n"
            + "Clark	,Green,	81 Flat Rock ,	San Francisco,	CA,	91119,	12/5/1957	,499091234";

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

        Date date = df.parse(raw[6]);
        params.put(ParameterName.BORNDATE_AS_DATE, date);

        params.put(ParameterName.SSN, raw[7]);

        return params;
    }

    public String sendSuccessTest(int i) throws Exception { 
        StringBuilder sb = new StringBuilder();
        
    //    for (int i = 0; i < successRecords.length; i++) {
            sb.append(  IdeologyBusinessLogic.get().verifyClient(buildMap(successRecords[i])) );
            sb.append("                ");
   //     }

        return sb.toString();
    }

    public static void main(String[] args) {
        testParsing();
    }
    
    public static void testParsing(){
     String xml = "<?xml version=\"1.0\"?><response>" +
"	<id-number>1765753765</id-number>" +
"	<summary-result>" +
"		<key>id.failure</key>" +
"		<message>FAIL</message>" +
"	</summary-result>" +
"	<results>" +
"		<key>result.match</key>" +
"		<message>ID Located</message>" +
"	</results>" +
"	<qualifiers>" +
"		<qualifier>" +
"			<key>resultcode.yob.within.one.year</key>" +
"			<message>YOB Does Not Match, Within 1 Year Tolerance</message>" +
"		</qualifier>" +
"		<qualifier>" +
"			<key>another.year</key>" +
"			<message>Thfis is anothfer qualifier</message>" +
"		</qualifier>" +
"	</qualifiers> " +
"</response>";
     
     IdeologyResponse response = IdeologyResponse.getFromXML(xml);
     
        System.out.println(response.toString());
    }
}
