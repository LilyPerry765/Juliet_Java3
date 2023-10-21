/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE336_Same_Seed_in_PRNG__basic_08.java
Label Definition File: CWE336_Same_Seed_in_PRNG__basic.label.xml
Template File: point-flaw-08.tmpl.java
*/
/*
* @description
* CWE: 336 Same Seed in PRNG
* Sinks:
*    GoodSink: canonicalize before validate
*    BadSink : hardcoded seed
* Flow Variant: 08 Control flow: if(private_returns_t()) and if(private_returns_f())
*
* */

package testcases.CWE336_Same_Seed_in_PRNG;

import testcasesupport.*;

import javax.servlet.http.*;
import java.security.SecureRandom;

public class CWE336_Same_Seed_in_PRNG__basic_08 extends AbstractTestCase
{

    /* The methods below always return the same value, so a tool
       should be able to figure out that every call to these
       methods will return true or return false. */
    private boolean private_returns_t()
    {
        return true;
    }

    private boolean private_returns_f()
    {
        return false;
    }

    public void bad() throws Throwable
    {
        if (private_returns_t())
        {
            final byte[] SEED = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
            SecureRandom r = new SecureRandom();
            /* FLAW: using the same seed can make the PRNG sequence predictable if the seed is known */
            r.setSeed(SEED);
            IO.writeLine("" + r.nextInt()); /* run this several times and notice that the bad values are always the same */
            IO.writeLine("" + r.nextInt());
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            SecureRandom r = new SecureRandom(); /* FIX: no explicit seed specified; produces far less predictable PRNG sequence */

            IO.writeLine("" + r.nextInt());
            IO.writeLine("" + r.nextInt());

        }
    }

    /* good1() changes private_returns_t() to private_returns_f() */
    private void good1() throws Throwable
    {
        if(private_returns_f())
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            final byte[] SEED = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
            SecureRandom r = new SecureRandom();
            /* FLAW: using the same seed can make the PRNG sequence predictable if the seed is known */
            r.setSeed(SEED);
            IO.writeLine("" + r.nextInt()); /* run this several times and notice that the bad values are always the same */
            IO.writeLine("" + r.nextInt());
        }
        else {

            SecureRandom r = new SecureRandom(); /* FIX: no explicit seed specified; produces far less predictable PRNG sequence */

            IO.writeLine("" + r.nextInt());
            IO.writeLine("" + r.nextInt());

        }
    }

    /* good2() reverses the bodies in the if statement */
    private void good2() throws Throwable
    {
        if(private_returns_t())
        {
            SecureRandom r = new SecureRandom(); /* FIX: no explicit seed specified; produces far less predictable PRNG sequence */
            IO.writeLine("" + r.nextInt());
            IO.writeLine("" + r.nextInt());
        }
        else {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */

            final byte[] SEED = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
            SecureRandom r = new SecureRandom();
            /* FLAW: using the same seed can make the PRNG sequence predictable if the seed is known */
            r.setSeed(SEED);

            IO.writeLine("" + r.nextInt()); /* run this several times and notice that the bad values are always the same */
            IO.writeLine("" + r.nextInt());

        }

    }

    public void good() throws Throwable
    {
        good1();
        good2();
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
