package ro.upt.ac.cloudputing.entities.publications;

import ro.upt.ac.cloudputing.entities.models.RecordList;
import ro.upt.ac.cloudputing.entities.models.RecordModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


// models a publication list extracted from Clarivate/WOS/ISI website
public class PublicationList extends LinkedList<Publication> {
    private static final long serialVersionUID = 6481372948922137567L;

    public PublicationList() {
    }

    // merging duplicates by WOS
    public void addAll(PublicationList pl) {
        for (Publication p1 : pl) {
            boolean bNotFound = true;
            for (Publication p2 : this) {
                if (p1.getWOS().equals(p2.getWOS())) {
                    bNotFound = false;
                }
            }

            if (bNotFound) {
                this.add(p1);
            }
        }
    }

    // creates a String representation
    public String toString() {
        String s = "";
        for (Publication p : this) {
            s += p + "\n";
        }
        return s;
    }

    // produces a map of table name, list of name value map
    public Map<String, RecordList> getPublicationsMap() {
        var map = new TreeMap<String, RecordList>();

        var listA = new RecordList();
        for (Publication p : this) {
            if (p.getDoctype().equals("Article")) {
                var mapA = new RecordModel();
                mapA.put("autori", p.getAuthors());
                mapA.put("titlul_lucrarii", p.getTitle());
                mapA.put("revista_detalii_bibliografice", 
                	(p.getSourceTitle().isEmpty() ? "" : p.getSourceTitle()) + 
            		(p.getBookSeriesTitle().isEmpty() ? "" : "," + p.getBookSeriesTitle()) + 
            		(p.getIssue().isEmpty() ? "" : ", issue " + p.getIssue()) + 
            		(p.getVolume().isEmpty() ? "" : ", volume " + p.getVolume()) + 
            		(p.getPages().isEmpty() ? "" : ", pages " + p.getPages()) + 
            		(p.getMonth().isEmpty() ? "" : ", " + p.getMonth())
                );
                mapA.put("tip_revista", null);
                mapA.put("anul", p.getYear());
                mapA.put("cod_doi", p.getDOI());
                mapA.put("cod_wos", p.getWOS());
                mapA.put("denumirea_jurnalului", p.getSourceTitle());
                mapA.put("cod_issn_online", p.getEISSN());
                mapA.put("cod_issn_print", p.getISSN());
                mapA.put("numar_autori", Integer.valueOf(p.getNoOfAuthors()));
                listA.add(mapA);
            }
        }
        map.put("table_GM2021_II_1_1", listA);
        //TableMetadata tm=new TableMetadataProvider().get("II1.1");
        //map.put(tm.dbTableName,listA);


        //---------------------------------------------------------------------

        var listP = new RecordList();
        for (Publication p : this) {
            if (p.getDoctype().equals("Proceedings Paper")) {
                var mapP = new RecordModel();
                mapP.put("autori", p.getAuthors());
                mapP.put("titlul_lucrarii", p.getTitle());
                mapP.put("manifestarea_stiintifica",
                	(p.getSourceTitle().isEmpty() ? "" : p.getSourceTitle()) + 
            		(p.getBookSeriesTitle().isEmpty() ? "" : "," + p.getBookSeriesTitle()) + 
            		(p.getIssue().isEmpty() ? "" : ", issue " + p.getIssue()) + 
            		(p.getVolume().isEmpty() ? "" : ", volume " + p.getVolume()) + 
            		(p.getPages().isEmpty() ? "" : ", pages " + p.getPages()) + 
            		(p.getMonth().isEmpty() ? "" : ", " + p.getMonth())
                );
                mapP.put("tip_de_manifestare_stiintifica", "indexate ISI/WOS");
                mapP.put("anul", p.getYear());
                mapP.put("cod_doi", p.getDOI());
                mapP.put("cod_wos", p.getWOS());
                mapP.put("denumirea_volumului", p.getSourceTitle());
                mapP.put("cod_issn_online", p.getEISSN());
                mapP.put("cod_issn_print", p.getISSN());
                mapP.put("cod_isbn", p.getISBN());
                mapP.put("numar_autori", Integer.valueOf(p.getNoOfAuthors()));
                listP.add(mapP);
            }
        }
        map.put("table_GM2021_II_1_2", listP);
        //tm=new TableMetadataProvider().get("II1.2");
        //map.put(tm.dbTableName,listA);

        return map;
    }
}
