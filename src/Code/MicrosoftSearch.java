package Code;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MicrosoftSearch {
	public String Buscar(String ulrString) {
		HttpClient httpclient = HttpClients.createDefault();

		try {

			URIBuilder builder = new URIBuilder(
					"https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/describe");

			builder.setParameter("maxCandidates", "1");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "8395fbad3f284de4b98ef4f5c16e0bec");

			// Request body
			StringEntity reqEntity = new StringEntity("{\"url\":" + "\"" + ulrString + "\"}");
			System.out.println("\"ulr\":" + "\"" + ulrString + "\"");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return null;
	}
}
