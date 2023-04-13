package com.practicaswrest.service;

public interface IEncryptService {

    String encryptpassword(String password);

    boolean verifypassword(String origialpassword, String hashpassword);


}
