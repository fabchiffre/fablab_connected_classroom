package commandRecognizer.openhab;

import rest.RestClient;

public class OpenHabRestBinding extends OpenHabBinding {

	private String m_baseUrl;
	private RestClient m_client;

	public OpenHabRestBinding(String baseUrl) {
		super();
		m_baseUrl = baseUrl;
		m_client = new RestClient();
	}

	public String getBaseUrl() {
		return m_baseUrl;
	}

	public RestClient getClient() {
		return m_client;
	}

	@Override
	protected Item createItem(String name) {
		return new RestItem(name, this);
	}

	public class RestItem extends OpenHabBinding.Item {

		private OpenHabRestBinding m_parent;

		private RestItem(String name, OpenHabRestBinding parent) {
			super(name);
			this.m_parent = parent;
		}

		@Override
		public String sendAction(String data) {
			return m_parent.getClient().executePostRequest(
					m_parent.getBaseUrl() + "/items/" + getName(), data);
		}

		@Override
		public String getInformation() {
			return m_parent.getClient().executeGetRequest(
					m_parent.getBaseUrl() + "/items/" + getName());
		}

	}
}
