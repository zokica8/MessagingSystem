package kurs.messaging.encryption;

import java.util.Scanner;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordEncryption {
	
	private static BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	
	public static String encryptPassword(String password) {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		String encrypted = encryptor.encryptPassword(password);
		if(encryptor.checkPassword(password, encrypted)) {
			log.info("Password encrypted!!");
			textEncryptor.setPassword(password);
			String encrypt = textEncryptor.encrypt(encrypted);
			System.out.println(encrypt);
			return encrypt;
		}
		else {
			log.info("Password not encrypted!!");
			String decrypt = textEncryptor.decrypt(password);
			return decrypt;
		}
	}
	
	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Password: ");
			String password = sc.nextLine();
			
			StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
			textEncryptor.setPassword(password);
			System.out.println("Encrypting text...");
			String encrypted = textEncryptor.encrypt(password);
			System.out.println(encrypted);
			System.out.println("Decrypting password...");
			String text = textEncryptor.decrypt(encrypted);
			System.out.println(text);
			
			String password2 = sc.nextLine();
			String result = encryptPassword(password2);
			System.out.println(result);
		}
	}
}
