package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

public class PackerTest {

    @Test
    public void packSuccessful() {
        String solution = Packer.pack("sample.txt");
        String expectedSolution = "4\r\n-\r\n2, 7\r\n8, 9\r\n";
        Assert.assertEquals(expectedSolution, solution);
    }

    @Test(expected = APIException.class)
    public void packFileNotFound() {
        Packer.pack("notFound");
    }
}
