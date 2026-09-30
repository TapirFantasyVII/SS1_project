package ssi.Utils;

import javax.crypto.Cipher;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class Firmador {
    public static byte[] firmar(String entidad, byte[] hash) throws Exception{
        //leer nuestra clave privada
        KeyFactory generadorClaves = KeyFactory.getInstance("RSA", "BC");
        byte[] clavePrivadaBytes = Files.readAllBytes(Paths.get("keys/"+entidad+".privada"));
        PrivateKey clavePrivada = generadorClaves.generatePrivate(
                new PKCS8EncodedKeySpec(clavePrivadaBytes)
        );
        Cipher cifradorAsimetrico = Cipher.getInstance("RSA", "BC");
        cifradorAsimetrico.init(Cipher.ENCRYPT_MODE, clavePrivada);
        return cifradorAsimetrico.doFinal(hash);
    }
}
