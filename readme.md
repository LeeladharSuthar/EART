# Encryption-Decryption Algorithm

**Overview**

This Java code implements a simple encryption-decryption algorithm that operates on 16-bit characters. It leverages the concept of binary tree reflections and a pseudo-random number generator (PRNG) to obfuscate the plaintext.

**Key Concepts:**

* **Key:** The encryption process relies on a `Key` object containing two essential parameters:
  * `n`: A large integer used for generating the initial reflection point.
  * `variance`: A smaller integer that influences the dynamic offset during encryption.
* **Binary Tree Reflection:** The core idea involves reflecting the binary representation of characters within a defined range. This reflection is determined by the `n` value of the key.
* **PRNG-Based Dynamic Offset:** A PRNG is employed to introduce randomness into the encryption process. The generated random number is used to calculate a dynamic offset, which further obfuscates the encrypted text.

**Encryption and Decryption Process:**

**Encryption:**
1. Calculate the initial reflection point based on `n`.
2. Generate a dynamic offset using the PRNG.
3. Apply the reflection and dynamic offset to each character.
4. Convert the modified character to its corresponding ASCII value.

**Decryption:**
1. Reverse the dynamic offset and reflection process.
2. Convert the decrypted ASCII value back to its original character.

**Usage:**

1. **Create a Key:** Instantiate a `Key` object with the desired `n` and `variance` values.
2. **Encrypt:** Call the `encrypt` method, passing the `Key` object and the plaintext string as arguments.
3. **Decrypt:** Call the `decrypt` method, passing the same `Key` object and the ciphertext string as arguments.

**Limitations and Considerations:**

* **Security:** The algorithm is relatively simple and may not be suitable for highly sensitive data. Stronger encryption techniques are available for more robust security.
* **Character Set:** The algorithm is designed for 16-bit characters. It may not be directly applicable to other character encodings.

**Potential Improvements:**

* **Key Strength:** Consider using stronger key generation techniques, such as cryptographic hash functions.
* **PRNG Quality:** Explore more sophisticated PRNG algorithms to improve randomness.
* **Character Set Handling:** Enhance the algorithm to support a wider range of character encodings.
* **Security Analysis:** Conduct a thorough security analysis to identify potential vulnerabilities.
