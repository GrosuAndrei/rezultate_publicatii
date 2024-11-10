package ro.upt.ac.cloudputing.webcrawlers.clarivate;

import com.google.gson.Gson;
import ro.upt.ac.cloudputing.entities.researchers.ResearcherIdMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("starting wos crawler...");
        test1();
        System.out.println("stopping wos crawler...");
    }

    public static void test1() {
        ClarivateService crawler1 = new ClarivateService(100, "", "D-1347-2011");
        crawler1.extractPublications();
        var pubMap = crawler1.getPublicationList().getPublicationsMap();
        var json = new Gson().toJson(pubMap);
        System.out.println(json);
    }

    public static void test2() {
        ClarivateService crawler2 = new ClarivateService(100, "Chirila, Ciprian-Bogdan", "");
        crawler2.extractPublications();
        System.out.println("rId=" + crawler2.getResearcherId());
        System.out.println("pMap=" + crawler2.getPublicationList().getPublicationsMap());

        ResearcherIdMap map2 = new ResearcherIdMap(crawler2.getPublicationList());
        map2.extract();
        System.out.println("map2=" + map2);
        System.out.println("rid[name]=" + map2.getResearcherId("Chirila, Ciprian-Bogdan"));
    }

    public static void test3() {
        ClarivateService crawler3 = new ClarivateService(200, "Precup, Radu-Emil", null);
        crawler3.extractPublications();
        System.out.println(crawler3.getPublicationList().getPublicationsMap().size());

        ResearcherIdMap map3 = new ResearcherIdMap(crawler3.getPublicationList());
        map3.extract();
        System.out.println(map3.getResearcherId("Precup, Radu-Emil"));
    }
    
    public static void test4() {
        ClarivateService crawler1 = new ClarivateService(100, "", "A-6993-2009");
        crawler1.extractPublications();
        var pubMap = crawler1.getPublicationList().getPublicationsMap();
        var json = new Gson().toJson(pubMap);
        System.out.println(json);
    }    
}
