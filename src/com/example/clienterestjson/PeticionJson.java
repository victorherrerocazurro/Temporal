package com.example.clienterestjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class PeticionJson extends AsyncTask<String, Void, Persona> {

	private TextView resultado;

	private Exception excepcion;

	public PeticionJson(TextView resultado) {
		super();
		this.resultado = resultado;
	}

	// Se ejecuta en el hilo secundario

	@Override
	protected Persona doInBackground(String... params) {

		InputStream is = null;
		
		try {
		
			URL url = new URL(params[0]);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");//POST, PUT, DELETE
			//Para post y put
			//connection.setDoOutput(true);
			//OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
			//osw.write("{\"id\":0,\"nombre\":\"Victor\"}");
			
			connection.connect();
			
			is = connection.getInputStream();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			
			String json = r.readLine();
			
			JSONObject jsonObject = new JSONObject(json);
			
			return new Persona(jsonObject.getInt("id"), jsonObject.getString("nombre"));

		} catch (Exception e) {
			e.printStackTrace();
			excepcion = e;
			return null;
		} finally {
			try {
				if (is != null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Se ejecutan en el hilo principal


	@Override
	protected void onPreExecute() {
		// Inicializar el ProgressDialog
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Persona result) {
		super.onPostExecute(result);

		if (excepcion == null) {
			// Proceso los resultados
			resultado.setText(result.getNombre() + result.getId());
		} else {
			// Aviso al usuario de que se ha producido una excepcion
			Log.e(this.getClass().getName(), excepcion.getMessage());
		}
	}
}
