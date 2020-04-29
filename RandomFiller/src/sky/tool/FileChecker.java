package sky.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileChecker
{
	private Encrypter encrypter;

	public FileChecker(Encrypter encrypter)
	{
		this.encrypter = encrypter;
	}

	public void check(String path)
	{
		try
		{
			encrypter.reset();
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[StaticParams.fileBuffersize];
			while(fis.read(buffer) != -1)
			{
				encrypter.update(buffer);
			}
			fis.close();
			if(file.getName().equals(encrypter.result()))
			{
				System.out.println("����һ��");
			}
			else
			{
				System.out.println("���ݳ���");
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
