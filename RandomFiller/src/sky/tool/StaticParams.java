package sky.tool;

public class StaticParams
{
	/**
	 * 密钥文件的名字
	 */
	public static final String keyFileName = "_key.bin";
	
	/**
	 * 加密算法名称
	 */
	public static final String keyEncryptionAlgorithm = "AES";
	
	/**
	 * 加密算法的秘钥长度有多少位
	 */
	public static final int keyByteLength = 16;

	/**
	 * 摘要算法名称
	 */
	public static final String digestAlgorithm = "SHA-256";
	
	/**
	 * 摘要算法输出的结果有多少位 
	 */
	public static final int digestAlgorithmOutputLength = 256;
	
	/**
	 * 每个buffer有多少byte数据，并加密同步一次
	 */
	public static final int fileBuffersize = 1024;
	
	/**
	 * 最后生成的文件大小是多少个fileBuffersize
	 */
	public static final int fileSize = 64 * 1024;
	
	/**
	 * 转换字典，将一个大数换成以该字典为进制的数
	 */
	
	public static final String dictionary = "UBCV34HKPJXTNGZ2E9W0MFRY167A5D8S";

	/**
	 * 生成文件的缩略名
	 */
	public static final String suffix = ".sky";
}
