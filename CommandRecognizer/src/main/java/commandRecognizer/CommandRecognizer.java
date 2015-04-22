package commandRecognizer;
import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;


public class CommandRecognizer extends LiveSpeechRecognizer {

	private static final String LIGHT_ON = "allume la lumière";
	private static final String LIGHT_OFF = "éteint la lumière";
	private static final String STOP = "stop";
	
	private LightCallback m_callback;

	public CommandRecognizer(Configuration configuration, LightCallback callback) throws IOException {
		super(configuration);
		m_callback = callback;
	}
	
	
	public void setCallback(LightCallback callback) {
		this.m_callback = callback;
	}
	
	public void recognizeCommand() {
		System.out.println("---- Start recognition ----");
		// Start recognition process pruning previously cached data.
		startRecognition(true);
		while (true) {
			String utterance = getResult().getHypothesis();
			System.out.println("Résultat " + utterance);
			if (utterance.endsWith(STOP))
				break;
			else if (utterance.endsWith(LIGHT_ON)) {
				m_callback.lightOn();
			} else if (utterance.endsWith(LIGHT_OFF)) {
				m_callback.lightOff();
			}
		}
		stopRecognition();
	}
	
	public interface LightCallback {
		public void lightOn();
		public void lightOff();
	}
}
