package com.example.clienterestjson;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView texto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button boton = (Button) findViewById(R.id.button1);
		
		texto = (TextView) findViewById(R.id.textView1);
		
		boton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PeticionJson peticionJson = new PeticionJson(texto);
				String url = "http://10.0.2.2:8080/14-JERSEY-WS-REST/Saludos/Hola/Victor/Herrero";		
				peticionJson.execute(url);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
