package ro.upt.ac.cloudputing.entities.publications;

// models a WOS publication
// it can be an article or a proceedings paper
public class Publication
{
	private int nResearcherId=-1;
	
	private String szWOS="";  
	private String szTitle=""; 
	private String szDoctype="";
	private String szPages="";
	private String szSourceTitle="";
	private String szBookSeriesTitle="";
	private String szIssue="";
	private String szVolume="";
	private String szMonth="";
	private String szYear="";
	private String szAuthors="";
	private String szKeywords="";
	private String szISBN="";
	private String szISSN="";
	private String szEISSN="";
	private String szDOI="";
	private String szContributorRIdIds="";
	private String szContributorRIdNames="";
	private int nNoOfAuthors=0;

	public Publication()
	{
	}

	public int getResearcherId() 
	{
		return nResearcherId;
	}

	public void setResearcherId(int nResearcherId) 
	{
		this.nResearcherId = nResearcherId;
	}
	
	public String getWOS() 
	{
		return szWOS;
	}

	public void setWOS(String szWOS) 
	{
		this.szWOS = szWOS;
	}

	public String getTitle() 
	{
		return szTitle;
	}

	public void setTitle(String szTitle) 
	{
		this.szTitle = szTitle;
	}

	public String getDoctype() 
	{
		return szDoctype;
	}

	public void setDoctype(String szDoctype) 
	{
		this.szDoctype = szDoctype;
	}

	public String getPages() 
	{
		return szPages;
	}

	public void setPages(String szPages) 
	{
		this.szPages = szPages;
	}

	public String getSourceTitle() 
	{
		return szSourceTitle;
	}

	public void setSourceTitle(String szSourceTitle) 
	{
		this.szSourceTitle = szSourceTitle;
	}
	
	public String getBookSeriesTitle() 
	{
		return szBookSeriesTitle;
	}

	public void setBookSeriesTitle(String szBookSeriesTitle) 
	{
		this.szBookSeriesTitle = szBookSeriesTitle;
	}

	public String getIssue() 
	{
		return szIssue;
	}

	public void setIssue(String szIssue) 
	{
		this.szIssue = szIssue;
	}

	public String getVolume() 
	{
		return szVolume;
	}

	public void setVolume(String szVolume) 
	{
		this.szVolume = szVolume;
	}

	public String getMonth() 
	{
		return szMonth;
	}

	public void setMonth(String szMonth) 
	{
		this.szMonth = szMonth;
	}

	public String getYear() 
	{
		return szYear;
	}

	public void setYear(String szYear) 
	{
		this.szYear = szYear;
	}

	public String getAuthors() 
	{
		return szAuthors;
	}

	public void setAuthors(String szAuthors) 
	{
		this.szAuthors = szAuthors;
	}

	public String getKeywords() 
	{
		return szKeywords;
	}

	public void setKeywords(String szKeywords) 
	{
		this.szKeywords = szKeywords;
	}

	public String getISBN() 
	{
		return szISBN;
	}

	public void setISBN(String szISBN) 
	{
		this.szISBN = szISBN;
	}

	public String getISSN() 
	{
		return szISSN;
	}

	public void setISSN(String szISSN) 
	{
		this.szISSN = szISSN;
	}

	public String getEISSN() 
	{
		return szEISSN;
	}

	public void setEISSN(String szEISSN) 
	{
		this.szEISSN = szEISSN;
	}

	public String getDOI() 
	{
		return szDOI;
	}

	public void setDOI(String szDOI) 
	{
		this.szDOI = szDOI;
	}
	
	public String getContributorRIdIds() 
	{
		return szContributorRIdIds;
	}

	public void setContributorRIdIds(String szContributorRIdIds) 
	{
		this.szContributorRIdIds = szContributorRIdIds;
	}

	public String getContributorRIdNames() 
	{
		return szContributorRIdNames;
	}

	public void setContributorRIdNames(String szContributorRIdNames) 
	{
		this.szContributorRIdNames = szContributorRIdNames;
	}
	
	public int getNoOfAuthors() 
	{
		return nNoOfAuthors;
	}

	public void setNoOfAuthors(int nNoOfAuthors) 
	{
		this.nNoOfAuthors = nNoOfAuthors;
	}

	public String toString()
	{
		return
			nResearcherId+","+
			szAuthors+","+
			szTitle+","+
			szSourceTitle+","+
			szBookSeriesTitle+","+
			szIssue+","+
			szVolume+","+
			szISBN+","+
			szISSN+","+
			szPages+","+
			szWOS+","+
			szDoctype+","+
			szMonth+","+
			szYear+","+
			szKeywords+","+
			szContributorRIdIds+","+
			szContributorRIdNames;
	}	
}
