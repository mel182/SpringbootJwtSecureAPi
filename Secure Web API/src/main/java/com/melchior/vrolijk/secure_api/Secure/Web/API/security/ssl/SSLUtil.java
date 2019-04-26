package com.melchior.vrolijk.secure_api.Secure.Web.API.security.ssl;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * This class represent the SSL utilities
 *
 * @author Melchior Vrolijk
 */
public final class SSLUtil
{
    //region List of trusted managers
    /**
     * Retrieve the array of {@link TrustManager}
     */
    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException { }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException { }

                public java.security.cert.X509Certificate[] getAcceptedIssuers(){ return null; }
            }
    };
    //endregion

    //region Turn off SSL checking procedure
    /**
     * Turn off SSL checking procedure by installing all-trusting trust manager
     * @throws NoSuchAlgorithmException Throws when algorithm is invalid
     * @throws KeyManagementException Throws when key management is invalid
     */
    public  static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException
    {
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init( null, UNQUESTIONING_TRUST_MANAGER, null );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    //endregion

    //region Turn off SSL checking
    /**
     * Turn off the on SSL checking procedure which return it to the initial state
     * @throws KeyManagementException Throws when algorithm is invalid
     * @throws NoSuchAlgorithmException Throws when key management is invalid
     */
    public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException
    {
        SSLContext.getInstance("SSL").init( null, null, null );
    }
    //endregion
}
