package com.example.imagenes_heroes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassConnection extends AsyncTask<String, String, String> {
    Context con;
    //Se usa una tarea asincrona por que para las conexiones es la mas adecuda
    //los 3 strings de arriba, son: la url, la cadena que procesa y la cadena que retorna
    @Override
    protected String doInBackground(String... strings) {

//Se instancian las variables para conexion
        HttpURLConnection httpURLConnection = null;
        URL url = null;

        try {
            url = new URL(strings[0]);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        try {
            //Crear la conexión para la url (para HTTPS usaríamos HttpsURLConnection)
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(10000);

            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("User-Agent", "cliente Android 1.0");
            // httpURLConnection.setRequestProperty("Authorization", "Basic " + Base64.encodeToString(("joe" + ":" + "pino").getBytes(), Base64.NO_WRAP));
            httpURLConnection.connect();
            int code = httpURLConnection.getResponseCode();
//Si la conexion es exitosa entra aqui
            if (code == httpURLConnection.HTTP_OK) {
//Lee el archivo Json y almacena la informacion en una cadena que se retorna al final
                InputStream inputStreamResponse = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamResponse, "UTF-8"));
                String linea = null;
                StringBuffer respuestaCadena = new StringBuffer();

                while ((linea = bufferedReader.readLine()) != null) {
                    respuestaCadena.append(linea);
                }
                if (inputStreamResponse != null) {
                    try {
                        inputStreamResponse.close();
                    } catch (IOException ex) {
                        Log.d(this.getClass().toString(), "Error cerrando InputStream", ex);
                    }
                }
                return respuestaCadena.toString();
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
