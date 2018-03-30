package edu.ucla.cs.scai.canali.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryParseException;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import edu.ucla.cs.scai.canali.core.experiment.qald6.CanaliW2VQASystem;
import edu.ucla.cs.scai.canali.core.index.utils2.GooglePoint;

/**
 * Servlet implementation class W2VQAServlet
 */
//@WebServlet("/W2VQAServlet")
public class W2VQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String QUERY = "q";
	
	public void getRis (String query, HashSet<GooglePoint> points, Model m, String nome)
	{
		try(QueryExecution qexec = QueryExecutionFactory.create(query,m))
		{
			ResultSet results = qexec.execSelect();
			while (results.hasNext())
			{
				QuerySolution soln = results.nextSolution();
				String indirizzo = soln.get("indirizzo").asNode().getLiteralValue().toString();
				String latitudine = soln.get("latitudine").asNode().getLiteralValue().toString();
				String longitudine = soln.get("longitudine").asNode().getLiteralValue().toString();
				if ((latitudine != null) || (longitudine!= null))
					points.add(new GooglePoint(nome, indirizzo,latitudine, longitudine));
			}
		}catch (QueryParseException e) {
			System.out.println("Non ha l'indirizzo e/o le coordinate!!!");
		}
	}
	
	void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String query = request.getParameter(QUERY);
		String path = System.getProperty("kb.index.dir");
		CanaliW2VQASystem qas;
		ArrayList<String> systAns = new ArrayList<String>();
		try {
			qas = new CanaliW2VQASystem(path + "/corpus_it40.vec.bin", path + "/supportFiles/property_labels");
			systAns = qas.getAnswer(query, null);
			String risposta = "", queryFinaleNome, queryFinale, a = null, aDef = null;
			
			HashSet<GooglePoint> points = new HashSet<>();
			ListIterator<String> listaRis = systAns.listIterator();
			
			int i = 0;
			float sommaLatitudine = 0, sommaLongitudine = 0;
			String queryStringNome = "PREFIX tia: <http://www.semanticweb.org/seven/ontologies/2016/6/Tourism_in_Apulia#>"
					+ "PREFIX wgs84: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
					+ "SELECT ?indirizzo ?latitudine ?longitudine WHERE {"
					+ "?posto tia:name \"";
			String fineQueryStringNome = "\"."
					+ "?posto tia:address ?indirizzo."
					+ "?posto wgs84:location ?coordinate."
					+ "?coordinate wgs84:lat ?latitudine."
					+ "?coordinate wgs84:long ?longitudine."
					+ "}" ;
			
			String queryString = "PREFIX tia: <http://www.semanticweb.org/seven/ontologies/2016/6/Tourism_in_Apulia#>"
					+ "PREFIX wgs84: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
					+ "SELECT ?indirizzo ?latitudine ?longitudine WHERE { ";
			String metaQueryString = " tia:address ?indirizzo. "; 
			String fineQueryString =
					  " wgs84:location ?coordinate. "
					+ "?coordinate wgs84:lat ?latitudine. "
					+ "?coordinate wgs84:long ?longitudine. "
					+ "} " ;
			
			String map = "<script>"
					+ " function initMap() {"
					+ " var map = new google.maps.Map("
					+ " document.getElementById('map'),"
					+ " {"
					+ " zoom: 10,"
					+ " center: {lat: ";
			while (listaRis.hasNext())
			{
				a = listaRis.next();
				Model m = ModelFactory.createDefaultModel().read(System.getProperty("sparql.endpoint"));
				if (a!=null)
				{
					System.out.println(a);
					risposta = risposta + "<p>" + a + "</p><br>";
					if (a.startsWith("http"))
					{
						aDef = a.replaceAll("http://www.semanticweb.org/seven/ontologies/2016/6/Tourism_in_Apulia#", "tia:");
						queryFinale = queryString + aDef + metaQueryString + aDef + fineQueryString;
						getRis(queryFinale, points, m, aDef);
					}
					else
					{
						queryFinaleNome = queryStringNome + a + fineQueryStringNome;
						getRis(queryFinaleNome, points, m, a);
					}
				}					
			}
			for (GooglePoint point: points)
			{
				++i;
				sommaLatitudine = sommaLatitudine + Float.parseFloat(point.getLatitudine());
				sommaLongitudine = sommaLongitudine + Float.parseFloat(point.getLongitudine());
			}
			map = map + sommaLatitudine/i + ", lng:" + sommaLongitudine/i + "}});";
			i = 0;
			for (GooglePoint point: points)
			{
				map = map + " var marker" + i + " = new google.maps.Marker("
						+ " {"
						+ " position: { lat: " + point.getLatitudine() + ", lng: " + point.getLongitudine() + "},"
						+ " map: map"
						+ " }"
						+ " );"
						+ " var infoWindow" + i + " = new google.maps.InfoWindow;"
						+ " var infowincontent" + i + " = document.createElement('div');"
						+ " var strong" + i + " = document.createElement('strong');"
						+ " strong" + i + ".textContent = \"" + point.getNome() + "\";"
						+ " infowincontent" + i + ".appendChild(strong" + i + ");"
						+ " infowincontent" + i + ".appendChild(document.createElement('br'));"
						+ " var text" + i + " = document.createElement('text');"
						+ " text" + i + ".textContent = \"" + point.getIndirizzo() + "\";"
						+ " infowincontent" + i + ".appendChild(text" + i + ");"
						+ " marker" + i + ".addListener('click', function() {"
						+ " infoWindow" + i + ".setContent(infowincontent" + i + ");"
						+ " infoWindow" + i + ".open(map, marker" + i + ");"
						+ " });";
				++i;
			}
			map = map + " }"
					+ " </script>"
					+ " <script async defer"
					+ " src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBWJVEc6Zb7po4Hxzukc6HBNfD5mrsGNm8&callback=initMap\">"
					+ " </script>";
			System.out.println(map);
			if (points.size() > 0)
				risposta = risposta + map;
			response.getWriter().write(risposta);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		/*try (PrintWriter out = response.getWriter()) {
		    out.print("[");
		    if (!res.isEmpty()) {
		        out.print(res.get(0).toJson());
		    }
		    for (int i=1; i<res.size(); i++) {
		        out.print(", "+res.get(i).toJson());
		    }
		    out.println("]");             
		}*/
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public W2VQAServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
}