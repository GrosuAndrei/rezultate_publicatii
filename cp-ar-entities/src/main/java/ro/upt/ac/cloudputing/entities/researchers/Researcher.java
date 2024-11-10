package ro.upt.ac.cloudputing.entities.researchers;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.TreeSet;

// models a researcher
public class Researcher
{
	private String szFullName="";

	// firstname and lastname split
	private String[] names=null;	

	private String szFirstName=null;
	private String szLastName=null;
	
	private String[] lastNames=null;
	private String[] firstNames=null;			
	
	// Cadariu-Brăiloiu, Liviu Ioan
	public Researcher(String szFullName)
	{
		this.szFullName=szFullName;
		initNames();
	}
	
	// initializes the last name and first name
	// it is expected that the last name and first name to be separated by a comma or a semicolon
	// Cadariu-Brăiloiu, Liviu Ioan
	private void initNames()
	{
		if(szFullName.contains(","))
		{
			names=szFullName.split(",");
			szLastName=names[0].trim();
			szFirstName=names[1].trim();
		}
		
		if(szFullName.contains(";"))
		{
			names=szFullName.split(";");
			szLastName=names[0].trim();
			szFirstName=names[1].trim();
		}

		lastNames=szLastName.split("\\s+");
		if(szLastName.contains("-"))
		{
			lastNames=szLastName.split("\\-");
		}

		firstNames=szFirstName.split("\\s+");
		if(szFirstName.contains("-"))
		{
			firstNames=szFirstName.split("\\-");
		}
	}

	// compares two researcher names
	// two reseachers are equal if the intersection of first names and last names is not be void
	public boolean equals(String szCandidateName)
	{
		Researcher candidate=new Researcher(szCandidateName);
		
		Set<String> setFirstNames=new TreeSet<String>();		
		for(String szName2:this.firstNames)
		{
			szName2=StringUtils.stripAccents(szName2);
			szName2=szName2.toLowerCase().trim();
			setFirstNames.add(szName2);
		}

		Set<String> setFirstNamesCandidate=new TreeSet<String>();		
		for(String szName2:candidate.firstNames)
		{
			szName2=StringUtils.stripAccents(szName2);
			szName2=szName2.toLowerCase().trim();
			setFirstNamesCandidate.add(szName2);
		}
		
		Set<String> setLastNames=new TreeSet<String>();		
		for(String szName2:this.lastNames)
		{
			szName2=StringUtils.stripAccents(szName2);
			szName2=szName2.toLowerCase().trim();
			setLastNames.add(szName2);
		}
		
		Set<String> setLastNamesCandidate=new TreeSet<String>();		
		for(String szName2:candidate.lastNames)
		{
			szName2=StringUtils.stripAccents(szName2);
			szName2=szName2.toLowerCase().trim();
			setLastNamesCandidate.add(szName2);
		}
		
		setFirstNames.retainAll(setFirstNamesCandidate);
		setLastNames.retainAll(setLastNamesCandidate);
				
		return !setFirstNames.isEmpty() && !setLastNames.isEmpty();
	}
	
	// computes all possible queries with last name and first name
	public Set<String> computeQueries()
	{
		Set<String> queries=new TreeSet<String>();
		
		// Cadariu-Brăiloiu, Liviu Ioan
		queries.add(szLastName+" "+szFirstName);

		/*
		Cadariu-Brăiloiu, Liviu
		Cadariu-Brăiloiu, Ioan
		*/
		if(firstNames!=null)
		{
			for(String firstName:firstNames)
			{
				queries.add(szLastName+" "+firstName);
			}
		}

		/*
		Cadariu, Liviu Ioan
		Brailoiu, Liviu Ioan
		*/
		if(lastNames!=null)
		{
			for(String lastName:lastNames)
			{
				queries.add(lastName+" "+szFirstName);
			}
		}

		/*
		Cadariu, Liviu
		Cadariu, Ioan
		Brăiloiu, Liviu
		Brăiloiu, Ioan
		*/
		if(lastNames!=null && firstNames!=null)
		{
			for(String lastName:lastNames)
			{
				for(String firstName:firstNames)
				{
					queries.add(lastName+" "+firstName);
				}
			}			
		}		
		
		// Cadariu-Brăiloiu, L* I*
		if(firstNames!=null)
		{
			String szSuffix="";
			for(String firstName:firstNames)
			{
				szSuffix+=firstName.charAt(0)+"* ";
			}
			queries.add(szLastName+" "+szSuffix);
		}
		
		return queries; 
	}	
}
