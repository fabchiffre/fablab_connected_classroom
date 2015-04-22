package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.entity.ByteArrayEntity;

public class RestClient {

	private HttpClient m_httpClient;

	public RestClient() {
		m_httpClient = HttpClientBuilder.create().build();
	}

	public String executeGetRequest(String targetURL) {
		// Create new getRequest with below mentioned URL
		HttpGet getRequest = new HttpGet(targetURL);

		// Execute your request and catch response
		try {
			HttpResponse response;
			response = m_httpClient.execute(getRequest);
			return getResponseString(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String executePostRequest(String targetURL, String data) {
		System.out.println("Target URL " + targetURL);
		HttpPost postRequest = new HttpPost(targetURL);
		// Execute your request and catch response
		try {
			ByteArrayEntity postDataEntity = new ByteArrayEntity(
					data.getBytes());
			postRequest.setEntity(postDataEntity);
			HttpResponse response;
			response = m_httpClient.execute(postRequest);
			return getResponseString(response);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getResponseString(HttpResponse response) {
		StringBuffer strBr = new StringBuffer();
		try {
			// Check for HTTP response code: 200 = success
			if (response.getStatusLine().getStatusCode() != 200
					&& response.getStatusLine().getStatusCode() != 201) {
				throw new IllegalStateException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			// Get-Capture Complete application/xml body response
			BufferedReader br;

			br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			String output;
			// Simply iterate through body response.
			while ((output = br.readLine()) != null) {
				strBr.append(output);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBr.toString();
	}
}
