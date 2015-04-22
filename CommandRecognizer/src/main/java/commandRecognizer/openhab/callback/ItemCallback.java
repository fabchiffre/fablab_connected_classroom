package commandRecognizer.openhab.callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import commandRecognizer.openhab.OpenHabBinding;

public abstract class ItemCallback {
	private List<OpenHabBinding.Item> m_itemList;

	public ItemCallback() {
		m_itemList = new ArrayList<OpenHabBinding.Item>();
	}

	public void addItem(OpenHabBinding.Item item) {
		m_itemList.add(item);
	}

	public List<OpenHabBinding.Item> getItemList() {
		return m_itemList;
	}
}
