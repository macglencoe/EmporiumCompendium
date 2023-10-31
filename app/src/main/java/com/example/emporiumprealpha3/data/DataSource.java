package com.example.emporiumprealpha3.data;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.emporiumprealpha3.model.Cigar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    JSONObject jsonObject;
    JSONArray cigarJsonArray;
    public static List<Cigar> Cigars;
    InputStream stream;
    public DataSource(File file) throws IOException, JSONException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        // This response will have Json Format String
        String response = stringBuilder.toString();

        jsonObject = new JSONObject(response);
        cigarJsonArray = new JSONArray(jsonObject.get("cigars"));

        Gson gson = new Gson();
        Type type = new TypeToken<List<Cigar>>(){}.getType();
        List<Cigar> cigarList = gson.fromJson(response, type);
        for (Cigar cigar : cigarList) {
            System.out.println(cigar.getTitle()+cigar.getId());
        }

    }
}
