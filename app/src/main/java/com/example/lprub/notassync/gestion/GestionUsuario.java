package com.example.lprub.notassync.gestion;

import android.util.Log;

import com.example.lprub.notassync.pojo.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class GestionUsuario {

    private String urlDestino = "http://192.168.1.28:8080/Netbeans/go";

    public boolean isValidUser(Usuario u){
        URL url = null;
        BufferedReader in = null;
        String res = "";
        String login;
        String pass;
        try {
            login = URLEncoder.encode(u.getEmail(), "UTF-8");
            pass = URLEncoder.encode(u.getPass(), "UTF-8");
            String destino = urlDestino+"?tabla=usuario&op=login&login="+login+"&pass="+pass+"&origen=android&accion=";
            url = new URL(destino);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                res += linea;
            }
            in.close();
            JSONObject obj = new JSONObject(res);
            return obj.getBoolean("r");
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }catch (JSONException e){
        }

        return false;
    }

}
