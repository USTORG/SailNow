package com.sailnow.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class OAuthRequest {

	private HttpClient httpClient;
	private  String requestType;
	private String requestUrl;
	List <NameValuePair> nvps;
	public OAuthRequest()
	{
		httpClient = HttpClients.createDefault();
		nvps = new ArrayList<NameValuePair>();
	}
	public void setRequest(String accessTokenRequestType) {
		this.requestType = accessTokenRequestType;
		
	}

	public void body(OAuthProperties prop) {
		this.addBodyNameValuePair(OAuth.OAUTH_CLIENT_ID, prop.getClientId());
		this.addBodyNameValuePair(OAuth.OAUTH_CLIENT_SECRET, prop.getClientSecret());		
	}

	public void addHeader(String contentType, String urlEncoded) {
		// TODO Auto-generated method stub
		
	}

	public void addBodyNameValuePair(String key, String value) {
		nvps.add(new BasicNameValuePair(key,value));
		
	}

	public void setRequestUrl(String requestUrl)
	{
		this.requestUrl = requestUrl;
	}
	public String executePostMethod(String url) {
		
		
	StringBuffer response = new StringBuffer();
	
		if(url == null)
		{
			
		}
		HttpPost post = new HttpPost(url);
		
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse httResponse = httpClient.execute(post);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    		httResponse.getEntity().getContent()));
	 
	        String inputLine;
	       
	 
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	 
	        // print result
	        System.out.println(response.toString());

		} catch (UnsupportedEncodingException e) {

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}


}
