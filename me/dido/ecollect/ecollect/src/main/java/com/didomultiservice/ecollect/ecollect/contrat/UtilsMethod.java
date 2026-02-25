package com.didomultiservice.ecollect.ecollect.contrat;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.*;

@Component
public class UtilsMethod {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(UtilsMethod.class);


    // @Async
    public <T, P> Response<T> callAPI(P data, String URL) {
        slf4jLogger.info("start method callAPI ON : " + URL);
        Request<P> reqToAPI = new Request<>();
        reqToAPI.setData(data);
        Response<T> response = new Response<>();
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        // requestHeaders.add("auth", paramsUtils.getCleESB());
        HttpEntity<Request<P>> requestEntity = new HttpEntity<Request<P>>(reqToAPI, requestHeaders);

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Make the HTTP POST request, marshaling the response from JSON
        ResponseEntity<Response<T>> res = restTemplate.exchange(URL, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<Response<T>>() {
                });

        response = res.getBody();
        if (response.isHasError()) {
            response.getStatus().setMessage("Erreur API : " + response.getStatus().getMessage());
        }

        slf4jLogger.info("end method callAPI");
        return response;
    }

    // @Async
    public <T, P> T callAPI(P data, String URL, boolean specific) {
        slf4jLogger.info("start method callAPI ON : " + URL);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        // requestHeaders.add("auth", paramsUtils.getCleESB());
        HttpEntity<P> requestEntity = new HttpEntity<P>(data, requestHeaders);

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Make the HTTP POST request, marshaling the response from JSON
        ResponseEntity<T> res = restTemplate.exchange(URL, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<T>() {
                });

        // response =res.getBody();
        // if(response.isHasError()){
        // response.getStatus().setMessage("Erreur API :
        // "+response.getStatus().getMessage());
        // }

        slf4jLogger.info("end method callAPI");
        return res.getBody();
    }

    public <T, P> List<T> callGetAPI(String URL, String bearer) {
        slf4jLogger.info("start method callGetAPI ON : " + URL);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        // requestHeaders.setContentType(new MediaType("application", "json"));
        if (Utilities.notBlank(bearer)) {
            requestHeaders.set("User-Agent", bearer);
        }

        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Make the HTTP GET request, marshaling the response from JSON
        ResponseEntity<List<T>> res = restTemplate.exchange(URL, HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<T>>() {
                });

        return res.getBody();
    }

    public String callINAPI(String URL, String agent) {
        slf4jLogger.info("start method callGetAPI ON : " + URL);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        if (Utilities.notBlank(agent)) {
            requestHeaders.set("User-Agent", agent);
        }

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("minsat", "minsat"));

        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Make the HTTP GET request, marshaling the response from JSON
        ResponseEntity<String> res = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, String.class);

        return res.getBody();
    }

    public List<Map<String, Object>> parseResult(String xmlResponse) {
        Document document = parseXmlFile(xmlResponse);

        List<Map<String, Object>> mapList = new ArrayList<>();

        NodeList cDRMA = document.getElementsByTagName("result");
        if (cDRMA != null) {
            int nbre = cDRMA.getLength();
            for (int i = 0; i < nbre; i++) {
                org.w3c.dom.Node node = cDRMA.item(i);
                Map<String, Object> map = new HashMap<String, Object>();
                slf4jLogger.info("cDRMA [" + i + "] ");
                for (int j = 0; j < node.getChildNodes().getLength(); j++) {
                    slf4jLogger.info(node.getChildNodes().item(j).getNodeName() + " : "
                            + node.getChildNodes().item(j).getTextContent());
                    if (!node.getChildNodes().item(j).getNodeName().equalsIgnoreCase("#text")) {
                        map.put(node.getChildNodes().item(j).getNodeName(),
                                node.getChildNodes().item(j).getTextContent());
                    }
                }
                mapList.add(map);
            }

        }
        return mapList;

    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public <T, P> T callGetOneAPI(String URL, String bearer) {
        slf4jLogger.info("start method callGetOneAPI ON : " + URL);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        if (Utilities.notBlank(bearer)) {
            requestHeaders.set("Authorization", String.format("Bearer %s", bearer));

//			String auth = "username" + ":" + "password";
//	         byte[] encodedAuth = Base64.encodeBase64( 
//	            auth.getBytes(Charset.forName("US-ASCII")) );
//	         String authHeader = "Basic " + new String( encodedAuth );
//	         requestHeaders.( "Authorization", authHeader );

//	         String plainCreds = "willie:p@ssword";
//	         byte[] plainCredsBytes = plainCreds.getBytes();
//	         byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
//	         String base64Creds = new String(base64CredsBytes);
//
//	         HttpHeaders headers = new HttpHeaders();
//	         headers.add("Authorization", "Basic " + base64Creds);

//			restTemplate.getInterceptors().add(
//					  new BasicAuthorizationInterceptor("username", "password"));
        }

        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Make the HTTP GET request, marshaling the response from JSON
        ResponseEntity<T> res = restTemplate.exchange(URL, HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<T>() {
                });

        return res.getBody();
    }

    public <T, P> T callAPIGET(String URL) {
        slf4jLogger.info("start method callGetOneAPI ON : " + URL);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));

        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Make the HTTP GET request, marshaling the response from JSON
        ResponseEntity<T> res = restTemplate.exchange(URL, HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<T>() {
                });

        return res.getBody();
    }

    public List<Map<String, Object>> callSMS(String URL) throws MalformedURLException, IOException, Exception {

        // Code to make a webservice HTTP request
        String outputString = "";
        Map<String, Object> map = new HashMap<String, Object>();
//  BASE64Encoder enc = new BASE64Encoder();
//  String userpassword = String.format("%s:%s", paramsUtils.getInUser(), paramsUtils.getInUserPass());
//  String encodedAuthorization = enc.encode(userpassword.getBytes());
        slf4jLogger.info(URL);

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder().url(URL).get()
                // .addHeader("User-Agent", "webcc/3.2/9.0")
                .addHeader("cache-control", "no-cache").build();

        okhttp3.Response response = client.newCall(request).execute();
        ResponseBody resp = response.body();
        outputString = resp.string();
//  slf4jLogger.info("outputString => "+outputString);
        List<Map<String, Object>> mapList = parseResult(outputString);
        return mapList;

    }

    @Async
    public List<Map<String, Object>> callSMSAsync(String URL) throws MalformedURLException, IOException, Exception {

        // Code to make a webservice HTTP request
        String outputString = "";
        Map<String, Object> map = new HashMap<String, Object>();
//  BASE64Encoder enc = new BASE64Encoder();
//  String userpassword = String.format("%s:%s", paramsUtils.getInUser(), paramsUtils.getInUserPass());
//  String encodedAuthorization = enc.encode(userpassword.getBytes());
        slf4jLogger.info(URL);

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder().url(URL).get()
                // .addHeader("User-Agent", "webcc/3.2/9.0")
                .addHeader("cache-control", "no-cache").build();

        okhttp3.Response response = client.newCall(request).execute();
        ResponseBody resp = response.body();
        outputString = resp.string();
        return parseResult(outputString);

    }


    public <P> String callSMSAPI(String FROM, String TO, String TEST, String IP) throws IOException {
        slf4jLogger.info("*****start method callSMSAPI ON*******");

        OkHttpClient client = new OkHttpClient();

        String URL = String.format(
                "http://%s/cgi-bin/sendsms?username=sva&password=kannelsva&smsc=identification1&mclass=1&from=%s&to=%s&text=%s",
                IP, FROM, TO, TEST);
        slf4jLogger.info(URL);

        okhttp3.Request request = new okhttp3.Request.Builder().url(URL).get().addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache").addHeader("Host", IP)
                .addHeader("accept-encoding", "gzip, deflate").addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache").build();
        okhttp3.Response response = client.newCall(request).execute();
        ResponseBody resp = response.body();
        String resString = resp.string();
        // Gson gson = new Gson();
        // String map = gson.fromJson(resString, new TypeToken<String>() {
        // }.getType());
        slf4jLogger.info("ResponseBody => " + resString);
        slf4jLogger.info("**** END CALL callSMSAPI******");
        return resString;
    }
}
