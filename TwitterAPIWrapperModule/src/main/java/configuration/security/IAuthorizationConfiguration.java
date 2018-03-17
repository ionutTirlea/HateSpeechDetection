package configuration.security;

/**
 * Created by ionut.tirlea on 01/10/2017.
 */
public interface IAuthorizationConfiguration {

    String getOAuthConsumerKey();

    String getOAuthConsumerSecret();

    String getOAuthAccessToken();

}
