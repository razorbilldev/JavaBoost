package com.namo.modules.multithread.gaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RealisticUncaughtExample implements Runnable {

    private final String configFilePath;
    private final String remoteUrl;

    public RealisticUncaughtExample(String configFilePath, String remoteUrl) {
        this.configFilePath = configFilePath;
        this.remoteUrl = remoteUrl;
    }

    @Override
    public void run() {
        try {
            String config = readConfigFile();
            System.out.println(config);
            String response = fetchRemoteData();
            System.out.println("Value"+response);
            int value = Integer.parseInt(response.trim());
            System.out.println("Processed: " + value * 10);
        } catch (IOException e) {
            System.err.println("IO/network error: " + e.getMessage());
        }
    }

    private String readConfigFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(configFilePath))) {
            return br.readLine();
        }
    }

    private String fetchRemoteData() throws IOException {
        URL url = new URL(remoteUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(4000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new IOException("HTTP " + status);
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Runnable task = new RealisticUncaughtExample(
                "null",
                "https://api.internal.company.com/v2/data"
        );
        Thread t = new Thread(task, "Background-Poller-1");
        t.setUncaughtExceptionHandler((thread, ex) -> {
            System.err.printf("[%s] UNCAUGHT: %s%n", thread.getName(), ex);
        });
        t.start();
        System.out.println("Main finished");
    }
}
