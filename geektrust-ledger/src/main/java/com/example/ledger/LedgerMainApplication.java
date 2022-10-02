package com.example.ledger;

import com.example.ledger.command.CommandExecutor;
import com.example.ledger.exceptions.InvalidNumberOfArgumentsException;
import com.example.ledger.exceptions.UnrecognizedCommandException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LedgerMainApplication {
	
	public static void main(String[] args) throws UnrecognizedCommandException, InvalidNumberOfArgumentsException {
		CommandExecutor commandExecutor = new CommandExecutor();
		
		String filePath = args[0];
		
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(
					fileLine -> {
						System.out.println("[INFO]: Executing line: " + fileLine);
						try {
							commandExecutor.executeCommand(fileLine);
						} catch (NullPointerException e) {
							throw new NullPointerException(e.getMessage());
						} catch (RuntimeException e) {
							throw new RuntimeException(e);
						}
					}
			);
		} catch (NullPointerException e) {
			System.out.println("[Exception]: " + e.getMessage());
		} catch (RuntimeException e) {
			System.out.println("[Exception]: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Exception]: " + e.getMessage() + " file does not exist !!!");
		}
	}
}