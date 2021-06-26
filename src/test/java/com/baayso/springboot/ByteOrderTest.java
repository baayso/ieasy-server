package com.baayso.springboot;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;

public class ByteOrderTest {

    public static void main(String[] args) throws IOException {

        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            System.out.println("native order: BIG_ENDIAN");
        }
        else {
            System.out.println("nativeOrder: LITTLE_ENDIAN");
        }

        int a = 1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(a);
        byte[] buf = byteArrayOutputStream.toByteArray();

        if (buf[0] == 1) {
            System.out.println("jvm order: LITTLE_ENDIAN");
        }
        else {
            System.out.println("jvm order: BIG_ENDIAN");
        }

    }

}
