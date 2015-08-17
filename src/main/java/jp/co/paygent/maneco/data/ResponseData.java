package jp.co.paygent.maneco.data;

public abstract class ResponseData extends BaseData {

	public final String object;

	public ResponseData(String object) {
		this.object = object;
	}
}
