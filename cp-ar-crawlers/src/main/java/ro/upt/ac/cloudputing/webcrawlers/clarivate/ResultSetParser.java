package ro.upt.ac.cloudputing.webcrawlers.clarivate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ro.upt.ac.cloudputing.entities.publications.Publication;
import ro.upt.ac.cloudputing.entities.publications.PublicationList;

/*
result set example:

{"QueryResult":{"QueryID":11,"RecordsSearched":74162487,"RecordsFound":37},
"Data":[
{"UT":"WOS:000695348300007",
"Title":{"Title":["Middle School Arithmetic Auto-Generative Learning Objects to Support Learning in the COVID-19 Pandemic"]},
"Doctype": ...
*/

// parses a JSON result set and produces a publication list
public class ResultSetParser 
{
	private int nResearcherId=-1;
	private StringBuffer sbContent;

	private JsonArray data=null;
	
	private int nQueryID=-1;
	private int nRecordsFound=-1;
	
	private PublicationList publicationList=new PublicationList();
	
	public ResultSetParser(int nResearcherId,StringBuffer sbContent)
	{
		this.nResearcherId=nResearcherId;
		this.sbContent=sbContent;

		// parsing the header
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(this.sbContent.toString());		
		JsonObject root=je.getAsJsonObject();		
		JsonObject queryResult=(JsonObject)root.get("QueryResult");		
		nQueryID=((JsonObject)queryResult).get("QueryID").getAsInt();
		nRecordsFound=((JsonObject)queryResult).get("RecordsFound").getAsInt();
		
		// setting up the body
		data=(JsonArray)root.get("Data");
	}
	
	public int getQueryID()
	{
		return nQueryID;
	}

	public int getRecordsFound()
	{
		return nRecordsFound;
	}

	public PublicationList getPublicationList()
	{
		return publicationList;
	}
	
	// parses the JSON data field and fulfills the publication list
	public void parse()
	{
		for(JsonElement publication:data)
		{
			Publication p=new Publication();
			p.setResearcherId(nResearcherId);
			
			// to get the WOS of the publication
			String szWOS=((JsonObject)publication).get("UT").getAsString();
			p.setWOS(szWOS);
			
			// to get the title
			JsonObject title=((JsonObject)publication).get("Title").getAsJsonObject();
			JsonArray tabTitle=title.get("Title").getAsJsonArray();
			String szTitle=tabTitle.get(0).getAsString();
			p.setTitle(szTitle);

			// to get the doctype
			JsonObject doctype=((JsonObject)publication).get("Doctype").getAsJsonObject();
			JsonArray tabDoctype=doctype.get("Doctype").getAsJsonArray();
			String szDoctype=tabDoctype.get(0).getAsString();
			p.setDoctype(szDoctype);

			// to get the source
			JsonObject source=((JsonObject)publication).get("Source").getAsJsonObject();
			
			// to get the pages
			JsonArray tabPages=source.get("Pages").getAsJsonArray();
			String szPages=tabPages.get(0).getAsString();
			p.setPages(szPages);

			// to get the source title
			JsonArray tabSourceTitle=source.get("SourceTitle").getAsJsonArray();
			String szSourceTitle=tabSourceTitle.get(0).getAsString();
			p.setSourceTitle(szSourceTitle);
			
			
			// to get the book series title
			String szBookSeriesTitle="";
			if(source.get("BookSeriesTitle")!=null)
			{
				JsonArray tabBookSeriesTitle=source.get("BookSeriesTitle").getAsJsonArray();
				szBookSeriesTitle=tabBookSeriesTitle.get(0).getAsString();
				p.setBookSeriesTitle(szBookSeriesTitle);
			}
			
			// to get the issue
			String szIssue="";
			if(source.get("Issue")!=null)
			{
				JsonArray tabIssue=source.get("Issue").getAsJsonArray();
				szIssue=tabIssue.get(0).getAsString();
				p.setIssue(szIssue);
			}
			
			// to get the volume
			String szVolume="";
			if(source.get("Volume")!=null)
			{
				JsonArray tabVolume=source.get("Volume").getAsJsonArray();
				szVolume=tabVolume.get(0).getAsString();
				p.setVolume(szVolume);
			}
			
			// to get the month
			String szMonth="";
			if(source.get("Published.BiblioDate")!=null)
			{
				JsonArray tabMonth=source.get("Published.BiblioDate").getAsJsonArray();
				szMonth=tabMonth.get(0).getAsString();
				p.setMonth(szMonth);
			}

			// to get the year
			JsonArray tabYear=source.get("Published.BiblioYear").getAsJsonArray();
			String szYear=tabYear.get(0).getAsString();
			p.setYear(szYear);
			
			// to get the authors
			String szAuthors="";
			JsonObject author=((JsonObject)publication).get("Author").getAsJsonObject();
			JsonArray tabAuthors=author.get("Authors").getAsJsonArray();
			for(JsonElement author2:tabAuthors)
			{
				String szAuthor=author2.getAsString();
				szAuthors+=szAuthor+"; ";
			}
			szAuthors=szAuthors.substring(0,szAuthors.length()-1);
			p.setAuthors(szAuthors);
			p.setNoOfAuthors(tabAuthors.size());

			// to get the keywords
			String szKeywords="";
			if(((JsonObject)publication).get("Keyword")!=null)
			{
				JsonObject keyword=((JsonObject)publication).get("Keyword").getAsJsonObject();
				if(keyword.get("Keywords")!=null)
				{
					JsonArray tabKeywords=keyword.get("Keywords").getAsJsonArray();
					for(JsonElement keyword2:tabKeywords)
					{
						String szKeyword=keyword2.getAsString();
						szKeywords+=szKeyword.replaceAll(",","; ")+"| ";
					}
					szKeywords=szKeywords.substring(0,szKeywords.length()-1);
				}
			}
			p.setKeywords(szKeywords);
			
			// to get other
			JsonObject other=((JsonObject)publication).get("Other").getAsJsonObject();

			// to get the isbn
			String szISBN="";
			if(other.get("Identifier.Eisbn")!=null)
			{
				JsonArray tabISBN=other.get("Identifier.Eisbn").getAsJsonArray();				
				szISBN=tabISBN.get(0).getAsString();
				if(szISBN.contains("*"))
				{
					szISBN="";
				}
				p.setISBN(szISBN);
			}
			
			// to get the issn
			String szISSN="";
			if(other.get("Identifier.Issn")!=null)
			{
				JsonArray tabISSN=other.get("Identifier.Issn").getAsJsonArray();				
				szISSN=tabISSN.get(0).getAsString();
				p.setISSN(szISSN);
			}
			
			// to get the eissn
			String szEISSN="";
			if(other.get("Identifier.Eissn")!=null)
			{
				JsonArray tabEISSN=other.get("Identifier.Eissn").getAsJsonArray();				
				szEISSN=tabEISSN.get(0).getAsString();
				p.setEISSN(szEISSN);
			}
			
			// to get the DOI
			String szDOI="";
			if(other.get("Identifier.Doi")!=null)
			{
				JsonArray tabDOI=other.get("Identifier.Doi").getAsJsonArray();				
				szDOI=tabDOI.get(0).getAsString();
				p.setDOI(szDOI);
			}
			
			// to get the contributor RIdIds and RIdNames
			String szContributorRIdIds="";
			String szContributorRIdNames="";
			if(other.get("Contributor.ResearcherID.ResearcherIDs")!=null)
			{
				JsonArray tabContributorRId=other.get("Contributor.ResearcherID.ResearcherIDs").getAsJsonArray();
				
				// to get the researcher id ids
				for(JsonElement contributorRId:tabContributorRId)
				{
					String szContributorRId=contributorRId.getAsString();
					szContributorRIdIds+=szContributorRId+"|";
				}
				szContributorRIdIds=szContributorRIdIds.substring(0,szContributorRIdIds.length()-1);
				p.setContributorRIdIds(szContributorRIdIds);
				
				// to get the researcher id names
				JsonArray tabContributorRIdNames=other.get("Contributor.ResearcherID.Names").getAsJsonArray();
				for(JsonElement contributorRIdName:tabContributorRIdNames)
				{
					String szContributorRIdName=contributorRIdName.getAsString();
					szContributorRIdNames+=szContributorRIdName+"| ";
				}
				szContributorRIdNames=szContributorRIdNames.substring(0,szContributorRIdNames.length()-1);
				p.setContributorRIdNames(szContributorRIdNames);
			}
			
			publicationList.add(p);
		}
	}	
}
