/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE256_Plaintext_Storage_of_Password__PropertiesFile_42.java
Label Definition File: CWE256_Plaintext_Storage_of_Password.label.xml
Template File: sources-sinks-42.tmpl.java
*/
/*
 * @description
 * CWE: 256 Plaintext Storage of a Password.  Read the password from a Properties file or a regular file.  In the good case, read the file from the console.
 * BadSource: PropertiesFile Read data from a .properties file (from the property named data)
 * GoodSource: Read data from the console using readLine
 * Sinks: basic
 *    GoodSink: Use decrypted data as password to connect to DB
 *    BadSink : Use data as password to connect to DB
 * Flow Variant: 42 Data flow: data returned from one method to another in the same class
 *
 * */

package testcases.CWE256_Plaintext_Storage_of_Password;

import testcasesupport.*;

import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Properties;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Logger;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.sql.SQLException;

public class CWE256_Plaintext_Storage_of_Password__PropertiesFile_42 extends AbstractTestCase
{

    private String bad_source() throws Throwable
    {
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        data = ""; /* init data */

        /* retrieve the property */
        Properties props = new Properties();
        FileInputStream finstr = null;
        try {
            finstr = new FileInputStream("../common/config.properties");
            props.load(finstr);

            data = props.getProperty("data");
        }
        catch( IOException ioe )
        {
            log_bad.warning("Error with stream reading");
        }
        finally {
            /* clean up stream reading objects */
            try {
                if( finstr != null )
                {
                    finstr.close();
                }
            }
            catch( IOException ioe )
            {
                log_bad.warning("Error closing buffread");
            }
        }

        return data;
    }

    public void bad() throws Throwable
    {
        String data = bad_source();

        java.util.logging.Logger log2 = java.util.logging.Logger.getLogger("local-logger");

        /* POTENTIAL FLAW: direct retrieval of plaintext password */
        Connection conn2 = null;
        try
        {
            conn2 = DriverManager.getConnection("192.168.105.23", "sa", data);
        }
        catch(SQLException se)
        {
            log2.warning("error getting database connection");
        }
        finally
        {
            try
            {
                if( conn2 != null )
                {
                    conn2.close();
                }
            }
            catch( SQLException se )
            {
                log2.warning("error closing conn2");
            }
        }

    }

    /* goodG2B() - use goodsource and badsink */
    private String goodG2B_source() throws Throwable
    {
        String data;

        Logger log_good = Logger.getLogger("local-logger");

        data = ""; /* init data */

        /* read user input from console with readLine*/
        BufferedReader buffread_g = null;
        InputStreamReader instrread = null;
        try {
            instrread = new InputStreamReader(System.in);
            buffread_g = new BufferedReader(instrread);
            data = buffread_g.readLine();
        }
        catch( IOException ioe )
        {
            log_good.warning("Error with stream reading");
        }
        finally {
            /* clean up stream reading objects */
            try {
                if( buffread_g != null )
                {
                    buffread_g.close();
                }
            }
            catch( IOException ioe )
            {
                log_good.warning("Error closing buffread_g");
            }
            finally {
                try {
                    if( instrread != null )
                    {
                        instrread.close();
                    }
                }
                catch( IOException ioe )
                {
                    log_good.warning("Error closing instrread");
                }
            }
        }

        return data;
    }

    private void goodG2B() throws Throwable
    {
        String data = goodG2B_source();

        java.util.logging.Logger log2 = java.util.logging.Logger.getLogger("local-logger");

        /* POTENTIAL FLAW: direct retrieval of plaintext password */
        Connection conn2 = null;
        try
        {
            conn2 = DriverManager.getConnection("192.168.105.23", "sa", data);
        }
        catch(SQLException se)
        {
            log2.warning("error getting database connection");
        }
        finally
        {
            try
            {
                if( conn2 != null )
                {
                    conn2.close();
                }
            }
            catch( SQLException se )
            {
                log2.warning("error closing conn2");
            }
        }

    }

    /* goodB2G() - use badsource and goodsink */
    private String goodB2G_source() throws Throwable
    {
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        data = ""; /* init data */

        /* retrieve the property */
        Properties props = new Properties();
        FileInputStream finstr = null;
        try {
            finstr = new FileInputStream("../common/config.properties");
            props.load(finstr);

            data = props.getProperty("data");
        }
        catch( IOException ioe )
        {
            log_bad.warning("Error with stream reading");
        }
        finally {
            /* clean up stream reading objects */
            try {
                if( finstr != null )
                {
                    finstr.close();
                }
            }
            catch( IOException ioe )
            {
                log_bad.warning("Error closing buffread");
            }
        }

        return data;
    }

    private void goodB2G() throws Throwable
    {
        String data = goodB2G_source();

        java.util.logging.Logger log2 = java.util.logging.Logger.getLogger("local-logger");

        InputStreamReader inread2 = null;
        BufferedReader bufread2 = null;
        Connection conn2 = null;

        try
        {
            /* Get encrypted password and decrypt it using key read from the console */
            /* Assume pw was encrypted using a secret passphrase and AES */
            inread2 = new InputStreamReader(System.in);
            bufread2 = new BufferedReader(inread2);
            String sKey = bufread2.readLine();
            SecretKeySpec key = new SecretKeySpec(sKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");

            cipher.init(Cipher.DECRYPT_MODE, key);

            /* FIX: password is stored encrypted */
            String pw = new String(cipher.doFinal(data.getBytes()));

            try
            {
                conn2 = DriverManager.getConnection("192.168.105.23", "sa", pw);
            }
            catch(SQLException se)
            {
                log2.warning("error getting database connection");
            }
            finally
            {
                try
                {
                    if( conn2 != null )
                    {
                        conn2.close();
                    }
                }
                catch( SQLException se )
                {
                    log2.warning("error closing conn2");
                }
            }
        }
        catch( IOException e )
        {
            log2.warning("Error reading streams");
        }
        finally
        {
            try
            {
                if( bufread2 != null )
                {
                    bufread2.close();
                }
            }
            catch( IOException e )
            {
                log2.warning("Error closing bufread2");
            }
            finally
            {
                try
                {
                    if( inread2 != null )
                    {
                        inread2.close();
                    }
                }
                catch( IOException e )
                {
                    log2.warning("Error closing inread2");
                }
            }
        }

    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }

}
