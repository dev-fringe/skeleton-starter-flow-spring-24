import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) {
		Map map = new HashMap<>();
		map.put("grant_type", "client_credentials");
		map.put("appkey","PSXYwqsOQtRrGcipGrX86DetPHfGoMRFZfe9");//PSXYwqsOQtRrGcipGrX86DetPHfGoMRFZfe9
		map.put("secretkey","JYOPpNbLAsLG++5CL4dAXdJ86aFQJKKa4+wQ4GReAxvCKy8O8rzs8BDwvyzahE70REvv4+g3QxM/I61arm/hiQEleCiUmcFXtJyLAjEpawvXRLMw5qamdQac72MY4UWZjGjWt8QkPx1YEwz8NZl/85TcZnJcyz5g5JkzKmxrE+KzNw2Bc+E=");
		Map m = new RestTemplate().postForObject("https://openapi.koreainvestment.com:9443/oauth2/Approval", map, Map.class);
		HttpHeaders headers = new HttpHeaders();
		KisHeader h= new KisHeader();
		h.setAuthorization("bearer " + m.get("approval_key"));
		headers.setAll(new ObjectMapper().convertValue(h, Map.class));
		KisOrderOverseasProfit p = new KisOrderOverseasProfit();
		HttpEntity<Map> entity = new HttpEntity<Map>(new ObjectMapper().convertValue(p, Map.class),headers);
		ResponseEntity<String> s = new RestTemplate().postForEntity("https://openapi.koreainvestment.com:9443/uapi/overseas-stock/v1/trading/inquire-period-profit", entity, String.class);
	}
}
