import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpRequest.BodyPublishers;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchDocuments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String username = "7065524629";
		String password = "12345";
		
		String server = "https://demo.devzone1.in";
		String token ="";
		try {
			token = loginApi(username,password,server);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String loginApi(String username , String password , String server) throws IOException, InterruptedException {
		
		String loginendPoint = "/consumer/api/v1/sign-in/";
		String jwtToken = "";
		String jsonString = "{ \"login_key\": \""+username+"\", \"password\": \""+password+"\"}";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(server+loginendPoint))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonString)) 
                .build();
		JSONObject jsonresponse;
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		jsonresponse = new JSONObject(response.body());
		System.out.println(response.body());
		jwtToken = jsonresponse.getString("jwt_access_token");	
	
		return jwtToken;
	}
	
	public List<Integer> searchDocuments(String server , String token,String query , String category) throws IOException, InterruptedException {
		String endpoints = "/jarvis-one/documents/search/";
		HttpClient client = HttpClient.newHttpClient();
		String jsonString = "{\"query\":\""+query+"\",\"category\":["+category+"\"]}";
		HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(server+endpoints))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonString))
                .header("Authorization", "Bearer " +token)
                .build();
		long start = System.nanoTime();
		HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
		

		
		long end = System.nanoTime();
		long timetaken = (end - start) / 1_000_000;
		List<Integer> ids = new ArrayList<>();
		JSONObject root = new JSONObject(response.body());
//		JSONObject data = root.getJSONObject("data");
//        JSONArray categories = data.getJSONArray("category");
		JSONArray matchedDocuments = root.getJSONObject("data").getJSONArray("category").getJSONObject(0).getJSONArray("matched_documents");
		for(int i =0 ; i<matchedDocuments.length();i++) {
			JSONObject doc = matchedDocuments.getJSONObject(i);
			ids.add(doc.getInt("id"));
		}
		return ids;
	}
	
	public void queryWithoutParent(String server , String token , String query , String type,int docNumber) {
		String endpoints = "/jarvis-one/documents/query/";
		HttpClient client = HttpClient.newHttpClient();
		String jsonString = "{\"doc_list\": ["+docNumber+"], \"query\": \""+query+"\", \"type\": \""+type+"\"}";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(server+endpoints))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(jsonString))
                .header("Authorization", "Bearer " +token)
                .build();
		HttpResponse<String> response = null;
		long start = System.nanoTime();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.nanoTime();
		long timetaken = (end - start) / 1_000_000;
		System.out.println((end - start) / 1_000_000);
		JSONObject root = new JSONObject(response.body());
		System.out.println(root);
	}

}
