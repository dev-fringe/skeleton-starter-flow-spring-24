import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KisHeader {
	@JsonProperty("content-type")
	private String contentType;
	private String authorization;
	private String appkey;
	private String appsecret;
	private String personalseckey;
	@JsonProperty("tr_id")
	private String trId;
	@JsonProperty("tr_cont")
	private String trCont;
	private String custtype;
	@JsonProperty("seq_no")
	private String seqNo;
	@JsonProperty("mac_address")
	private String macAddress;
	@JsonProperty("phone_number")
	private String phoneNumber;
	@JsonProperty("ip_addr")
	private String ipAddr;
	private String hashkey;
	@JsonProperty("gt_uid")
	private String gtUid;
}
