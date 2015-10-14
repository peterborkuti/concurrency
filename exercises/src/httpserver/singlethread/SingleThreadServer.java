package httpserver.singlethread;

import httpserver.common.ServerUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import common.Util;

public class SingleThreadServer {
	public static final int PORT = 8182;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(PORT);
		Util.log("ServerSocket is opened");

		while (true) {
			Util.log("Waiting for client");
			Socket clientSocket = socket.accept();
			Util.log("client connection is accepted");
			ServerUtil.handleRequest(clientSocket);
			Util.log("client request handled");
			clientSocket.close();
			Util.log("client socket closed");
		}

	}

}
