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
				System.out.println("数据一致");
			}
			else
			{
				System.out.println("数据出错");
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
