package commandRecognizer.openhab;

import java.security.InvalidParameterException;
import java.util.HashMap;

public abstract class OpenHabBinding {

	private HashMap<String, OpenHabBinding.Item> m_itemMap;

	public OpenHabBinding() {
		m_itemMap = new HashMap<String, Item>();
	}

	public Item getItem(String name) {
		return m_itemMap.get(name);
	}

	public Item registerItem(String name) {
		if (m_itemMap.containsKey(name))
			throw new InvalidParameterException("Name is already used.");
		return m_itemMap.put(name, createItem(name));
	}

	protected abstract Item createItem(String name);

	public abstract class Item {

		private String m_name;

		protected Item(String name) {
			this.m_name = name;
		}

		public String getName() {
			return m_name;
		}

		public int hashCode() {
			return m_name.hashCode();
		}

		public abstract String sendAction(String data);

		public abstract String getInformation();
	}
}
