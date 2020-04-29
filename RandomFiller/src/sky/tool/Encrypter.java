package sky.tool;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encrypter
{
	/**
	 * 密码，用于加密。
	 */
	private Cipher cipher;
	
	/**
	 * 数据摘要算法
	 */
	private MessageDigest md;
	
	/**
	 * 以字典的进制描述的话，需要多少位才可以描述加密后的长度。
	 */
	private int filenamelength;
	
	public Encrypter(Key key)
	{
		try
		{
			cipher = Cipher.getInstance(StaticParams.keyEncryptionAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			md = MessageDigest.getInstance(StaticParams.digestAlgorithm);
		}
		catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e)
		{
			e.printStackTrace();
		}
		
		BigInteger mark = pow(new BigInteger("2"), StaticParams.digestAlgorithmOutputLength);
		BigInteger tester = new BigInteger("1");
		BigInteger radix = new BigInteger(String.valueOf(StaticParams.dictionary.length()));
		for(filenamelength = 1 ; filenamelength < StaticParams.digestAlgorithmOutputLength ; filenamelength ++)
		{
			tester = tester.multiply(radix);
			if(tester.compareTo(mark) > 0)
			{
				break;
			}
		}
	}
	
	public void update(byte[] byteArray)
	{
		try
		{
			md.update(cipher.doFinal(byteArray));
		}
		catch (IllegalBlockSizeException | BadPaddingException e)
		{
			System.out.println("加密错误");
			e.printStackTrace();
		}
	}
	
	public void reset()
	{
		md.reset();
	}
	
	public String result()
	{
		char[] dictionaryArray = StaticParams.dictionary.toCharArray();
		BigInteger digest = new BigInteger(toBigInteger(md.digest())) , radix = new BigInteger(String.valueOf(dictionaryArray.length));
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < filenamelength ;  i++)
		{
			sb.insert(0, dictionaryArray[digest.divide(pow(radix, i)).remainder(radix).intValue()]);
		}
		sb.append(StaticParams.suffix);
		return sb.toString();
	}
	
	/**
	 * 保证 最高位为1 使得大数为正数。
	 * @param digest
	 * @return
	 */
	private byte[] toBigInteger(byte[] digest)
	{
		byte[] temp = new byte[digest.length + 1];
		temp[0] = 0x00;
		for(int i = 0 ; i < digest.length ; i++)
		{
			temp[i + 1] = digest[i];
		}
		return temp;
	}
	/**
	 * 循环算乘方， math.pow超范围了
	 * @param bigInteger
	 * @param times
	 * @return
	 */
	private BigInteger pow(BigInteger bigInteger, int times)
	{
		if(times <= 0)
		{
			return new BigInteger("1");
		}
		BigInteger result = new BigInteger("1");
		for(int i = 0 ; i < times ; i ++)
		{
			result = result.multiply(bigInteger);
		}
		return result;
	}
}
