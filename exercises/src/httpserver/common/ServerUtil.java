package httpserver.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.Util;

public class ServerUtil {

	public static void handleRequest(Socket clientSocket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null && !"".equals(inputLine) ) {
			Util.log("Client said:" + inputLine);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

		out.println("Hello World");

		clientSocket.close();
	}

}
