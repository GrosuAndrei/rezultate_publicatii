package ro.upt.ac.cloudputing.webcrawlers.clarivate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;

import ro.upt.ac.cloudputing.entities.publications.PublicationList;
import ro.upt.ac.cloudputing.entities.researchers.Researcher;

// extracts publications from WOS by researcher name or by researcher ID
// uses as search query different names like 
// chirila ciprian
// chirila bogdan
// chirila c* b*
public class ClarivateService {
    private int nResearcherId = -1;
    private String szResearcherName = null;
    private String szResearcherId = null;
    private PublicationList publicationList = new PublicationList();

    public ClarivateService(int nResearcherId, String szAuthorName, String szResearcherId) {
        this.nResearcherId = nResearcherId;
        this.szResearcherName = szAuthorName;
        this.szResearcherId = szResearcherId;
    }

    public String getResearcherName() {
        return szResearcherName;
    }

    public String getResearcherId() {
        return szResearcherId;
    }

    public PublicationList getPublicationList() {
        return publicationList;
    }

    // extracting the publications by name or by researcher id
    public void extractPublications() {
        List<StringBuffer> listResults = new LinkedList<StringBuffer>();
        if (szResearcherId != null && !szResearcherId.isEmpty()) {
            System.out.println("crawling for " + szResearcherId);
            listResults = extractByResearcherId(szResearcherId);
        } else {
            Researcher r = new Researcher(szResearcherName);
            Set<String> queries = r.computeQueries();
            for (String szQuery : queries) {
                System.out.println("crawling for " + szQuery);
                listResults.addAll(extractByAuthorName(szQuery));
            }
        }

        // centralizing the results
        for (StringBuffer sbResult : listResults) {
            ResultSetParser rsp = new ResultSetParser(nResearcherId, sbResult);
            rsp.parse();
            publicationList.addAll(rsp.getPublicationList());
        }
    }

    // extracting the publications of a researcher given by name
    public List<StringBuffer> extractByAuthorName(String szAuthorName) {
        return extract("(AU=" + szAuthorName + " AND OG=(Polytechnic University of Timisoara))");
    }

    // extracting the publications of a researcher given by Researcher ID
    public List<StringBuffer> extractByResearcherId(String szResearcherId) {
        return extract("(AI=" + szResearcherId + ")");
    }

    // extracting publications by a WOS LITE API search string
    private List<StringBuffer> extract(String szSearchString) {
        List<StringBuffer> listResults = new LinkedList<StringBuffer>();

        StringBuffer sbPart;
        int nFirstRecord = 1;
        int nCount = 50;

        sbPart = extract(szSearchString, nFirstRecord, nCount);
        ResultSetParser parser = new ResultSetParser(this.nResearcherId, sbPart);
        int nTotal = parser.getRecordsFound();
        listResults.add(sbPart);

        for (nFirstRecord = nCount + 1; nFirstRecord < nTotal; nFirstRecord += nCount) {
            sbPart = extract(szSearchString, nFirstRecord, 50);
            listResults.add(sbPart);
        }

        return listResults;
    }

    // extracting publications by set setting first record and count
    private StringBuffer extract(String szSearchString, int nFirstRecord, int nCount) {
        System.out.println("extract " + nFirstRecord + " " + nCount);
        StringBuffer sbContent = null;
        URL url = null;
        try {
            String szUrl = "https://api.clarivate.com/api/woslite/?" +
                    "databaseId=WOS" +
                    "&usrQuery=" + URLEncoder.encode(szSearchString) +
                    "&firstRecord=" + nFirstRecord +
                    "&count=" + nCount;

            url = new URL(szUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-ApiKey", "76df1db77ec30fb7e0f731054d5506aa068c4aef");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            sbContent = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                sbContent.append(inputLine);
            }
            in.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sbContent;
    }
}
