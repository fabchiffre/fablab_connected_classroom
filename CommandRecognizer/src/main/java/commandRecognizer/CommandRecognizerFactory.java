package commandRecognizer;
import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;


public class CommandRecognizerFactory {

	private static final String ACOUSTIC_MODEL = "resource:/lium_french_f0";
	private static final String DICTIONARY_PATH = "resource:/frenchWords62K.dic";
	private static final String GRAMMAR_PATH = "resource:/grammar";
	
	private static CommandRecognizerFactory s_instance = new CommandRecognizerFactory();
	
	private static Configuration s_configuration;
	
	public static CommandRecognizerFactory getInstance() {
		return s_instance;
	}
	
	public CommandRecognizer createCommandRecognizer(CommandRecognizer.LightCallback callback) throws IOException {
		if(s_configuration == null)
			initBaseConfiguration();
		
		return new CommandRecognizer(s_configuration, callback);
	}

	private void initBaseConfiguration() {
		s_configuration = new Configuration();

		// Set path to acoustic model.
		s_configuration.setAcousticModelPath(ACOUSTIC_MODEL);
		// Set path to dictionary.
		s_configuration.setDictionaryPath(DICTIONARY_PATH);
		s_configuration.setGrammarPath(GRAMMAR_PATH);
		s_configuration.setUseGrammar(true);
		s_configuration.setGrammarName("dialog");
	}
}
