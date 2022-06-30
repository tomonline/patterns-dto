package com.example.demo.domain;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "AppUser")
@Data
@NoArgsConstructor
public class User {

    private static SecretKeySpec KEY = initKey();

    static SecretKeySpec initKey(){
        try {
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String password;
	@ManyToMany
	private List<Role> roles;
	
	public User(String name, String password, List<Role> roles) {
		this.name = Objects.requireNonNull(name);
		this.password = this.encrypt(password);
		this.roles = Objects.requireNonNull(roles);
	}
	
	public String encrypt(String password) {
		
		Objects.requireNonNull(password);
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, KEY);
			final byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
			return new String(encryptedBytes, StandardCharsets.UTF_8);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // do nothing
            return "";
		}
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}

}
