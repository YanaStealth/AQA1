package io.ctdev.Lesson2;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class DataProviderParameters1 {
  private String login;
  private String password;
  private String name;

  @Factory(dataProvider ="dataMethod")
  public DataProviderParameters1(String login, String password, String name){
      this.login=login;
      this.password=password;
      this.name=name;
  }

    @DataProvider
    public static Object[][] dataMethod(){
      return new Object[][] {{"Igor@gmail.com", "123qwe" , "Igor"}, {"Igor.gmail.com", "123qwe" , "Igor"},{"1", "123qwe" , "Igor"}};
    }

    @Test()
    public void registerNewUserVerificationOnMainPage () {
        System.out.println("Register new user on Main Page: login:"+ login+ "password:"+password+ "name:"+name);
    }
    @Test()
    public void registerNewUserVerificationFromBasketPage () {
        System.out.println("Register new user from basket Page: login:"+ login+ "password:"+password+ "name:"+name);
    }

}
