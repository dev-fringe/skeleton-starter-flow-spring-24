import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KisHeader2 {
	@JsonProperty("content-type")
	private String contentType;
	private String appkey;
	private String appsecret;
	
	public KisHeader2() {
		this.contentType ="application/json; charset=utf-8";
	}
}
