/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author USER
 */
public class User {
    
  private String password;
  private  String nickname;
  
  private final static User INSTANCE = new User();
  
  private User() {}
  
  public static User getInstance() {
    return INSTANCE;
  }
  
  public void setPassword(String s) {
    this.password = s;
  }
  
   public void setNickname(String s) {
    this.nickname = s;
  }
  
  public String getPassword() {
    return this.password;
  }
  public String getNickname() {
    return this.nickname;
  }

}

