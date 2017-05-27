package Code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

public class Pictureinfo {

	public static ArrayList<String> getInfo(String URL) {
		MicrosoftSearch buscar = new MicrosoftSearch();
		ArrayList listares = new ArrayList();

		String res = buscar.Buscar(URL);
		Json data = new Json();
		data.setJsonData(res);
		Json description = new Json();

		String Uno = (String) data.Get("description");
		data.setJsonData(Uno);
		String dos = method(data.Get("captions"));
		description.setJsonData(dos);
		String tags = data.Get("tags");
		tags = tags.substring(1);
		int index = 0;
		String tag = "";
		int numtags = 0;
		while (numtags != 3) {
			while (Character.toString(tags.charAt(index)).compareTo(",") != 0) {
				tag += Character.toString(tags.charAt(index));
				index++;

			}
			index++;

			listares.add(tag);
			tag = "";
			numtags++;
		}

		listares.add(description.Get("text"));
		return listares;

	}

	public static String method(String str) {
		str = str.substring(1);
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
}
