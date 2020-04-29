package sky.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class Generater
{
	private Encrypter encrypter;
	
	private Random random;
	
	private File storage;
	

	public Generater(Encrypter encrypter , String path)
	{
		super();
		this.encrypter = encrypter;
		this.random = new Random();
		this.storage = new File(path);
	}

	public void generate()
	{
		try
		{
			encrypter.reset();
			File newFile = new File(storage, UUID.randomUUID().toString() + ".temp");
			FileOutputStream fos = new FileOutputStream(newFile);
			byte[] buffer = new byte[StaticParams.fileBuffersize];
			for(int i = 0 ; i < StaticParams.fileSize ; i ++)
			{
				random.nextBytes(buffer);
				encrypter.update(buffer);
				fos.write(buffer);
			}
			fos.close();
			File renameFile = new File(storage, encrypter.result());
			newFile.renameTo(renameFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
