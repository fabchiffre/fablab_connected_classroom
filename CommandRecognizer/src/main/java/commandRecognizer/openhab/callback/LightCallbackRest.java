package commandRecognizer.openhab.callback;

import commandRecognizer.CommandRecognizer.LightCallback;

public class LightCallbackRest extends ItemCallback implements LightCallback {

	public void lightOn() {
		for (commandRecognizer.openhab.OpenHabBinding.Item item : getItemList()) {
			item.sendAction("ON");
		}
	}

	public void lightOff() {
		for (commandRecognizer.openhab.OpenHabBinding.Item item : getItemList()) {
			item.sendAction("OFF");
		}
	}

}
