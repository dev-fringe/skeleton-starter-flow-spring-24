import java.net.InetAddress;
import java.net.NetworkInterface;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.SneakyThrows;

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
	
	public KisHeader() {
		this.macAddress = getMacAddress();
		this.ipAddr = getIpAddress();
		this.phoneNumber = "01025053776";
		this.custtype = "P";
		this.contentType ="application/json; charset=utf-8";
		this.trId = "TTTS3039R";
	}

	@SneakyThrows
	public String getMacAddress() {
		InetAddress ipAddress = InetAddress.getLocalHost();
		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
		byte[] macAddressBytes = networkInterface.getHardwareAddress();
		StringBuilder macAddressBuilder = new StringBuilder();
		for (int macAddressByteIndex = 0; macAddressByteIndex < macAddressBytes.length; macAddressByteIndex++) {
			String macAddressHexByte = String.format("%02X", macAddressBytes[macAddressByteIndex]);
			macAddressBuilder.append(macAddressHexByte);
			if (macAddressByteIndex != macAddressBytes.length - 1) {
				macAddressBuilder.append(":");
			}
		}
		return macAddressBuilder.toString();
	}
	
	@SneakyThrows
	public String getIpAddress() {
		InetAddress ipAddress = InetAddress.getLocalHost();
		return ipAddress.getHostAddress();
	}
	
	@SneakyThrows
	public static void main(String[] args) {
		InetAddress ipAddress = InetAddress.getLocalHost();
		System.out.println(ipAddress.getHostAddress());
	}
}
