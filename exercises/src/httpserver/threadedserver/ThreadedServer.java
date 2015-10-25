package httpserver.threadedserver;

import httpserver.common.ServerUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Util;

class RequestHandler implements Runnable {

	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			ServerUtil.handleRequest(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Messenger {
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Messenger(int THREADS, int PORT) throws IOException {
		super();
		this.isRunning = true;
		this.executor = Executors.newFixedThreadPool(THREADS);
		this.serverSocket = new ServerSocket(PORT);
	}

	private boolean isRunning;
	private ExecutorService executor;
	private ServerSocket serverSocket;


}

class ShutdownHook extends Thread {
	private Messenger messenger;

	public ShutdownHook(Messenger messenger) {
		this.messenger = messenger;
	}

	public void run() {
		messenger.setRunning(false);
		Util.shutdownAndAwaitTermination(messenger.getExecutor());
		Util.log("executor terminated");
		try {
			messenger.getServerSocket().close();
			Util.log("socket closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class ThreadedServer {
	public static final int PORT = 8182;
	private static final int THREADS = 8;
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Messenger messenger = new Messenger(THREADS, PORT);

		ShutdownHook hook = new ShutdownHook(messenger);

		Runtime.getRuntime().addShutdownHook(hook);

		Util.log("ServerSocket is opened");

		while (messenger.isRunning()) {
			Util.log("Waiting for client");
			Socket clientSocket = messenger.getServerSocket().accept();
			Util.log("client connection is accepted");
			Util.log("socket remote port:" + clientSocket.getPort() + ", local port:" + clientSocket.getLocalPort());
			messenger.getExecutor().submit(new RequestHandler(clientSocket));
		}

		Util.log("App exits");
	}

}
