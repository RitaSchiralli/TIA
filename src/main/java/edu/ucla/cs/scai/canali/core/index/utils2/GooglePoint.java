package edu.ucla.cs.scai.canali.core.index.utils2;

public class GooglePoint {
	
	private String nome;
	private String indirizzo;
	private String latitudine;
	private String longitudine;
	
	public GooglePoint()
	{
	}
	
	public GooglePoint(String name, String ind, String lat, String lon)
	{
		nome = name;
		indirizzo = ind;
		latitudine = lat;
		longitudine = lon;
	}
	
	public void setNome(String name)
	{
		nome = name;
	}
	
	public void setIndirizzo(String ind)
	{
		indirizzo = ind;
	}
	
	public void setLatitudine(String lat)
	{
		latitudine = lat;
	}
	
	public void setLongitudine(String lon)
	{
		longitudine = lon;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public String getIndirizzo()
	{
		return indirizzo;
	}
	
	public String getLatitudine()
	{
		return latitudine;
	}
	
	public String getLongitudine()
	{
		return longitudine;
	}
}
