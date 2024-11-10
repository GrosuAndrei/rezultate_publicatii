package ro.upt.ac.cloudputing.entities.researchers;

import ro.upt.ac.cloudputing.entities.publications.Publication;
import ro.upt.ac.cloudputing.entities.publications.PublicationList;

import java.util.HashMap;
import java.util.Map;

// models the researcher name, id map extracted from publications 
public class ResearcherIdMap extends HashMap<String,String>
{
	private static final long serialVersionUID = 1704694124818491062L;
	private PublicationList publicationList=null; 

	public ResearcherIdMap(PublicationList publicationList)
	{
		this.publicationList=publicationList;
	}

	// iterates each publication
	public void extract()
	{
		for(Publication p:publicationList)
		{
			String szNames=p.getContributorRIdNames();
			String szRIds=p.getContributorRIdIds();
			extractRIds(szNames, szRIds);
		}
	}
	
	// extracts the names and rids from the publications into the map
	private void extractRIds(String szNames,String szRIds)
	{
		String[] ridNames=szNames.split("\\|");
		String[] ridIds=szRIds.split("\\|");
		
		if(ridNames.length==ridIds.length)
		{			
			for(int i=0;i<ridNames.length;i++)
			{
				if(!ridNames[i].isEmpty())
				{
					this.put(ridNames[i].trim(), ridIds[i].trim());
				}
			}
		}
	}
	
	// gets the researcher id of researcher person by name or null
	public String getResearcherId(String szCandidateName)
	{
		Researcher candidate=new Researcher(szCandidateName);
		
		// to iterate the UPT list of researchers
		for(String szResearcherName:this.keySet())
		{
			// to compare the names with specific algorithm
			if(candidate.equals(szResearcherName))
			{
				return this.get(szResearcherName);
			}
		}
		
		return null;
	}
	
	// creates a string representation of the map
	public String toString()
	{
		String szMap="";
		for(Entry<String,String> me:this.entrySet())
		{
			szMap+=me.getKey()+", "+me.getValue()+"\n";
		}
		
		return szMap;
	}
}
