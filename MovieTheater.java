package com.example.loginactivity;

import android.content.Context;
import android.os.StrictMode;

import com.example.loginactivity.Movie;
import com.example.loginactivity.Theater;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class MovieTheater {

    DocumentBuilder builder;
    private String _genre;
    private String _name;
    private String _year;
    //private String title;

    //public static ArrayList<Theater> theatre_list = new ArrayList<Theater>();
    public static ArrayList<Movie> movie_list = new ArrayList<Movie>();
    Context context = null;

    private static MovieTheater instance;

    public static MovieTheater getInstance() {
        if (instance == null) {
            instance = new MovieTheater();
        }
        return instance;
    }

    // Retrieves list movies from Finnkino url and adds them into a list of movies to be used later
    private MovieTheater() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    _genre = element.getElementsByTagName("Genres").item(0).getTextContent();
                    _name = element.getElementsByTagName("Title").item(0).getTextContent();
                    _year = element.getElementsByTagName("ProductionYear").item(0).getTextContent();
                    System.out.println(_genre + " " + _name + " " + _year);
                    movie_list.add(new Movie(_name, _genre, _year));
                }

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    // Get the objects from movie list and make into a list of strings
    public static ArrayList<String> returnMovieList() {
        ArrayList<String> ml = new ArrayList<>();
        for (int i = 0; i < movie_list.size(); i++) {
            Movie m = movie_list.get(i);
            String l = (m.getTitle() + "; " + m.getGenre() + "; " + m.getYear());
            ml.add(l);
        }
        return ml;
    }
}




