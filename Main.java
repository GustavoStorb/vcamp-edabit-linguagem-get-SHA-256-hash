import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) {
        // teste 1
        System.out.println(getSha256Hash("password123"));
        // teste 2
        System.out.println(getSha256Hash("Fluffy@home"));
        // teste 3
        System.out.println(getSha256Hash("Hey dude!"));
    }

	private static StringBuilder transformBytesToHex(byte[] hash) {

		// Crio um stringbuilder com o limite máximo de 2x a quantidade de bytes que vou usar para dar append nos hex
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		// Faço um looping em todos os bytes para transformar em hex
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString;
	}

	public static String getSha256Hash(String str) {
		try {
			// Instâncio a classice que vou utilizar para gerar os bytes da hash
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// Pego os bytes da string do parametro da função e transformo em bytes de hash
			byte[] encodedhash = digest.digest(str.getBytes(StandardCharsets.UTF_8));

			// Transformo bytes para hex e dou um .toString() para transformar em string no final.
			return transformBytesToHex(encodedhash).toString();
		} catch(Exception err) { // Exception caso der algum erro retorne a mensagem
			err.printStackTrace();
			return "Unexpected Error";
		}
	}
}