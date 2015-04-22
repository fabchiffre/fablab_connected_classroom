package commandRecognizer;

import java.io.IOException;

import commandRecognizer.openhab.OpenHabBinding;
import commandRecognizer.openhab.OpenHabBinding.Item;
import commandRecognizer.openhab.OpenHabRestBinding;
import commandRecognizer.openhab.callback.LightCallbackRest;

public class CommandRecognizerLauncher {

	public static void main(String args[]) throws IOException {
		OpenHabBinding binding = new OpenHabRestBinding(
				"http://localhost:8080/rest");
		Item it = binding.registerItem("Light");
		LightCallbackRest lightCallbackRest = new LightCallbackRest();
		lightCallbackRest.addItem(binding.getItem("Light"));

		CommandRecognizer recognizer = CommandRecognizerFactory.getInstance()
				.createCommandRecognizer(lightCallbackRest);
		recognizer.recognizeCommand();
	}
}
