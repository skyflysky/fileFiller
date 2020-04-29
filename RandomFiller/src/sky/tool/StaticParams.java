package sky.tool;

public class StaticParams
{
	/**
	 * ��Կ�ļ�������
	 */
	public static final String keyFileName = "_key.bin";
	
	/**
	 * �����㷨����
	 */
	public static final String keyEncryptionAlgorithm = "AES";
	
	/**
	 * �����㷨����Կ�����ж���λ
	 */
	public static final int keyByteLength = 16;

	/**
	 * ժҪ�㷨����
	 */
	public static final String digestAlgorithm = "SHA-256";
	
	/**
	 * ժҪ�㷨����Ľ���ж���λ 
	 */
	public static final int digestAlgorithmOutputLength = 256;
	
	/**
	 * ÿ��buffer�ж���byte���ݣ�������ͬ��һ��
	 */
	public static final int fileBuffersize = 1024;
	
	/**
	 * ������ɵ��ļ���С�Ƕ��ٸ�fileBuffersize
	 */
	public static final int fileSize = 64 * 1024;
	
	/**
	 * ת���ֵ䣬��һ�����������Ը��ֵ�Ϊ���Ƶ���
	 */
	
	public static final String dictionary = "UBCV34HKPJXTNGZ2E9W0MFRY167A5D8S";

	/**
	 * �����ļ���������
	 */
	public static final String suffix = ".sky";
}
