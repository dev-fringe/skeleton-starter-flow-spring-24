import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KisOrderOverseasProfit {
	@JsonProperty("CANO")
	private String CANO;
	@JsonProperty("ACNT_PRDT_CD")
	private String acntPrdtCd;
	@JsonProperty("OVRS_EXCG_CD")
	private String ovrsExcgCd;
	@JsonProperty("NATN_CD")
	private String natnCd;
	@JsonProperty("CRCY_CD")
	private String crcyCd;
	@JsonProperty("PDNO")
	private String pdno;
	@JsonProperty("INQR_STRT_DT")
	private String inqrStrtDt;
	@JsonProperty("INQR_END_DT")
	private String inqrEndDt;
	@JsonProperty("WCRC_FRCR_DVSN_CD")
	private String wcrcFrcrDvsnCd;
	@JsonProperty("CTX_AREA_FK200")
	private String ctxAreaFk200;
	@JsonProperty("CTX_AREA_NK200")
	private String ctxAreaNk200;
}
