package test.tts;

import java.util.Locale;

import test.ttos.R;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class TTSActivity extends ActionBarActivity implements OnInitListener , OnClickListener{

	private TextToSpeech tts;
	private EditText inputText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tts_main);
		init();
		tts = new TextToSpeech(this, this);
	}

	private void init() {
		inputText = (EditText) findViewById(R.id.inputText);
		findViewById(R.id.speakButton).setOnClickListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (tts == null) return;
		tts.stop();
		tts.shutdown();
	}

	@SuppressWarnings("deprecation")
	public void speakText() {
		String toSpeak = inputText.getText().toString();
		Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();
		tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
	}

	@SuppressLint("NewApi")
	public void speakTextNew() {
		String toSpeak = inputText.getText().toString();
		Toast.makeText(this, "New Api: " + toSpeak, Toast.LENGTH_SHORT).show();
		tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "Vision+");
	}

	@Override
	public void onInit(int status) {
		if (status != TextToSpeech.ERROR) tts.setLanguage(Locale.UK);
	}

	@Override
	public void onClick(View v) {
		if (Build.VERSION.SDK_INT >= 21) speakTextNew();
		else speakText();
	}

}
