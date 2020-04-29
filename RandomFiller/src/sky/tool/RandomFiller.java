package sky.tool;

import java.security.Key;

public class RandomFiller
{
	//private static final String[] startParam = {"O:\\Filler","encrypt","50"};
	
	//private static final String[] startParam = {"O:\\Filler","check","O:\\Filler\\UHRJPD7A6SCC1UMG3JM2DSZYDWESSVE5BJ5RZBZ6YFY198G13PX4.sky"};
	
	public static void main(String[] args)
	{
		//args = startParam;
		
		ParamCheck keychecker = new ParamCheck();
		int workmode = keychecker.formatCheck(args);
		if(workmode >= 0)
		{
			System.out.println("第" + workmode + "个参数格式校验错误");
			return;
		}
		Key key = keychecker.keyCheck(args[0]);
		if(key == null)
		{
			System.out.println("秘钥错误");
			return;
		}
		System.out.println("秘钥正确");
		Encrypter encrypter = new Encrypter(key);
		switch (workmode)
		{
			//加密生成模式
			case -1:
			{
				int count = Integer.valueOf(args[2]);
				Generater generater = new Generater(encrypter , args[0]);
				for(int i = 0 ; i < count ; i++)
				{
					System.out.println("正在生成第" + (i + 1) + "个文件");
					generater.generate();
				}
				break;
			}
			//解密校验模式
			case -2:
			{
				FileChecker Filechecker = new FileChecker(encrypter);
				Filechecker.check(args[2]);
				break;
			}
			default:
			{
				System.out.println("该模式待开发");
			}
			break;
		}
		System.out.println("程序结束");
		
	}
}
