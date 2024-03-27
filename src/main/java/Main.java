import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Main {

	@SneakyThrows
	public static void main(String[] args) {
		String appkey = "PSXYwqsOQtRrGcipGrX86DetPHfGoMRFZfe9";
		String secretkey = "JYOPpNbLAsLG++5CL4dAXdJ86aFQJKKa4+wQ4GReAxvCKy8O8rzs8BDwvyzahE70REvv4+g3QxM/I61arm/hiQEleCiUmcFXtJyLAjEpawvXRLMw5qamdQac72MY4UWZjGjWt8QkPx1YEwz8NZl/85TcZnJcyz5g5JkzKmxrE+KzNw2Bc+E=";
		Map map = new HashMap<>();
		map.put("grant_type", "client_credentials");
		map.put("appkey", appkey);
		map.put("secretkey", secretkey);
		Map m = new RestTemplate().postForObject("https://openapi.koreainvestment.com:9443/oauth2/Approval", map, Map.class);
		HttpHeaders headers = new HttpHeaders();
		HttpHeaders headers2 = new HttpHeaders();
		KisHeader2 h= new KisHeader2();
//		h.setAuthorization("bearer " + m.get("approval_key"));
		h.setAppkey(appkey);
		h.setAppsecret(secretkey);
		
		
		KisHeader h2= new KisHeader();
		h2.setAuthorization("bearer " + m.get("approval_key"));
		h2.setAppkey(appkey);
		h2.setAppsecret(secretkey);
		Map header= new ObjectMapper().convertValue(h, Map.class);
		headers.setAll(header);
		System.out.println(header);
		KisOrderOverseasProfit p = new KisOrderOverseasProfit();
		String jsonBody = new ObjectMapper().writeValueAsString(p);
		Map mm = new HashMap<>();
		mm.put("JsonBody", jsonBody);
		HttpEntity<Map> entity = new HttpEntity<Map>(new ObjectMapper().convertValue(p, Map.class),headers);
		HttpEntity<Map> entity2 = new HttpEntity<Map>(new ObjectMapper().convertValue(p, Map.class),headers);
		ResponseEntity<Map> re = new RestTemplate().postForEntity("https://openapi.koreainvestment.com:9443/uapi/hashkey",entity2, Map.class);
		System.out.println(re);
		ResponseEntity<String> s = new RestTemplate().postForEntity("https://openapi.koreainvestment.com:9443/uapi/overseas-stock/v1/trading/inquire-period-profit", entity, String.class);
	}
}
