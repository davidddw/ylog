package org.livecloud.ylog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;

public class Test {

	private static final String toPdfTool = "c:\\Program Files\\EditPlus\\editplus.exe";

	public PrintResultHandler print(String destPath, final long printJobTimeout, final boolean printInBackground) 
			throws ExecuteException, IOException {
		File file = new File(destPath);

		CommandLine cmdLine = new CommandLine(toPdfTool);
		Map<String, File> map = new HashMap<String, File>();
		map.put("file", file);
		cmdLine.addArgument("${file}");
		cmdLine.setSubstitutionMap(map);

		DefaultExecutor executor = new DefaultExecutor();

		// 设置执行命令成功的退出值为1
		executor.setExitValue(0);

		int exitValue;
		ExecuteWatchdog watchdog = null;
		PrintResultHandler resultHandler;

		if (printJobTimeout > 0) {
			watchdog = new ExecuteWatchdog(printJobTimeout);
			executor.setWatchdog(watchdog);
		}

		// pass a "ExecuteResultHandler" when doing background printing
		if (printInBackground) {
			System.out.println("[print] Executing non-blocking print job  ...");
			resultHandler = new PrintResultHandler(watchdog);
			executor.execute(cmdLine, resultHandler);
		} else {
			System.out.println("[print] Executing blocking print job  ...");
			exitValue = executor.execute(cmdLine);
			resultHandler = new PrintResultHandler(exitValue);
		}

		// 非阻塞
		//DefaultExecuteResultHandler resultHandler = new PrintResultHandler();

//		boolean result = true;
//		try {
//			executor.execute(cmdLine, resultHandler);
//			resultHandler.waitFor();
//		} catch (Exception e) {
//			result = false;
//			e.printStackTrace();
//		}

		return resultHandler;
	}

	private class PrintResultHandler extends DefaultExecuteResultHandler {
		private ExecuteWatchdog watchdog;

		public PrintResultHandler(final ExecuteWatchdog watchdog) {
			this.watchdog = watchdog;
		}

		public PrintResultHandler(final int exitValue) {
			super.onProcessComplete(exitValue);
		}

		@Override
		public void onProcessComplete(final int exitValue) {
			super.onProcessComplete(exitValue);
			System.out.println("[resultHandler] The document was successfully printed ...");
		}

		@Override
		public void onProcessFailed(final ExecuteException e) {
			super.onProcessFailed(e);
			if (watchdog != null && watchdog.killedProcess()) {
				System.err.println("[resultHandler] The print process timed out");
			} else {
				System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws Exception {

		final long printJobTimeout = 0;
		final boolean printInBackground = true;

		PrintResultHandler printResult;
		Test t =new Test();

		try {
			// printing takes around 10 seconds
			System.out.println("[main] Preparing print job ...");
			printResult = t.print("d:\\autotest.yml", printJobTimeout, printInBackground);
			System.out.println("[main] Successfully sent the print job ...");
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.println("[main] Printing of the following document failed ");
			throw e;
		}
		
		try {
			// printing takes around 10 seconds
			System.out.println("[main] Preparing print job ...");
			printResult = t.print("d:\\autotest.yml", printJobTimeout, printInBackground);
			System.out.println("[main] Successfully sent the print job ...");
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.println("[main] Printing of the following document failed ");
			throw e;
		}

		// come back to check the print result
		System.out.println("[main] Test is exiting but waiting for the print job to finish...");
		//printResult.waitFor();
		System.out.println("[main] The print job has finished ...");

	}
}
