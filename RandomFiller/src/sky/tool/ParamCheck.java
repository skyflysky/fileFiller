package sky.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ParamCheck
{
	public int formatCheck(String[] args)
	{
		if(args.length != 3)
		{
			System.out.println("入参数量不对");
			return 0;
		}
		for(int i = 0 ; i < 3 ; i++)
		{
			if(args[i] == null || args[i].length() == 0)
			{
				return i + 1;
			}
		}
		int workmode = 0;
		switch (args[1])
		{
			case "encrypt":
			{
				workmode = -1;
				int count;
				try
				{
					count = Integer.valueOf(args[2]);
				}
				catch (NumberFormatException e)
				{
					return 3;
				}
				System.out.println("加密生成模式，将生成" + count + "个文件");
				break;
			}
			case "check":
			{
				workmode = -2;
				File inputFile = new File(args[2]);
				if(!inputFile.exists())
				{
					return 3;
				}
				break;
			}
			default:
				workmode = 0;
				break;
		}
		return workmode;
	}
	
	public Key keyCheck(String storagePath)
	{
		File storageFile = new File(storagePath);
		if(!storageFile.exists())
		{
			if(!storageFile.mkdir())
			{
				System.out.println("尝试创建" + storageFile.getAbsolutePath() + "文件夹创建失败");
				return null;
			}
		}
		else if(!storageFile.isDirectory())
		{
			System.out.println("第一个参数不是一个文件夹");
			return null;
		}
		File keyFile = new File(storageFile , StaticParams.keyFileName);
		try
		{
			if(!keyFile.exists())
			{
				if(keyFile.createNewFile())
				{
					generateKey(keyFile);
				}
			}
			FileInputStream keyInputStream = new FileInputStream(keyFile);
			byte[] keyByte = new byte[StaticParams.keyByteLength];
			int readed = keyInputStream.read(keyByte);
			keyInputStream.close();
			if(readed != StaticParams.keyByteLength)
			{
				System.out.println("秘钥文件读取失败");
				return null;
			}
			System.out.println("秘钥文件:" + keyFile.getAbsolutePath());
			return new SecretKeySpec(keyByte,StaticParams.keyEncryptionAlgorithm);
		}
		catch (IOException e)
		{
			System.out.println("秘钥生成/解析失败");
			e.printStackTrace();
			return null;
		}
	}

	private void generateKey(File keyFile) throws IOException
	{
		KeyGenerator keyGenerator = null;
		try
		{
			keyGenerator = KeyGenerator.getInstance(StaticParams.keyEncryptionAlgorithm);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		keyGenerator.init(new SecureRandom());
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();
		FileOutputStream keyOutputStream = new FileOutputStream(keyFile);
		keyOutputStream.write(byteKey);
		keyOutputStream.close();
	}
}
