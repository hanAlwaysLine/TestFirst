package cn.cinema.manage.util;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import com.sun.jna.Library;
import com.sun.jna.Native;
/**
 *   TEA (algorithm) (128bit)
 *   (Tiny Encrypt algorithm) 32 rounds
 *   @author jiyk
 *
 */
public class TEA {
	
	 String keyPath=ServletActionContext.getServletContext().getRealPath("/DLL");
		public TEA()
		{
			keyPath=ServletActionContext.getServletContext().getRealPath("/DLL");
		}
		  private static tea NATIVE;
		  static {
		    try {
		      //启动保护操作系统模式
		      Native.setProtected(true);
		      String path=ServletActionContext.getServletContext().getRealPath("/DLL");
		      if(path!=null)
		      {
		      System.out.println("path="+path);
		      NATIVE = (tea) Native.loadLibrary(path+"\\TEA", tea.class);  
		      }else{
		    	  NATIVE = (tea) Native.loadLibrary("TEA", tea.class);  
		    	  System.out.println("path is null!");
		      }
		    } catch (UnsatisfiedLinkError e) {
		      System.err.println("TEA.dll or libTEA.so file not found");
		      e.printStackTrace();
		    }
		  }
		  public interface tea extends Library {
		   int TEA_encryptStrFile(String plainStr,String keyStr,String path);
		   int TEA_encryptFile(String keyStr,String path);
		   int TEA_decryptFile(String keyStr,String path);
		   public String TEA_encrypt(String plainStr,String keyStr);
		   public String TEA_decrypt(String cipherStr,String keyStr);
		  }
		  public static void main(String args[]) {
			  TEA t=new TEA();
					  }
		  //encrypt for specified str and path with filename
		  public static int TEA_encryptStrFile(String plainStr,String keyStr,String path)
		  {
			return  NATIVE.TEA_encryptStrFile(plainStr, keyStr,path);
		  }
		//encrypt for specified path with filename
		  public static int TEA_encryptFile(String keyStr,String path)
		  {
			return  NATIVE.TEA_encryptFile(keyStr,path);
		  }
		  //decrypt for specified path with filename
		  public static int TEA_decryptFile(String keyStr,String path)
		  {
			  return NATIVE.TEA_decryptFile(keyStr,path);
		  }
		  //encrypt for str and return str by reference
		  public String TEA_encrypt(String plainStr,String keyStr)
		  {
			  byte[] plainByte=plainStr.getBytes();
			  String hexStr=this.bytesToHexString(plainByte);
			  String result="";
			 result=NATIVE.TEA_encrypt(hexStr, keyStr);
			   return result;
		  }
		  //decrypt for str and return str by reference
		  public String TEA_decrypt(String cipherStr,String keyStr)
		  {
			  String result="";
			  result=NATIVE.TEA_decrypt(cipherStr, keyStr);
			  System.out.println("result:"+result);
			  byte[] resultByte=this.hexStringToByte(result);
			  return new String(resultByte);
		  }
		  public byte[] hexStringToByte(String hex) {
				int len = (hex.length() / 2);
				byte[] result = new byte[len];
				char[] arraychar = hex.toCharArray();
				for (int i = 0; i < len; i++) {
					int pos = i * 2;
					result[i] = (byte) (toByte(arraychar[pos]) << 4 | toByte(arraychar[pos + 1]));// 两个十六进制数拼接成一个字节
				}// 低十六进制数放高四位,高十六进制数放低四位
				return result;
			}
			private static byte toByte(char c) {
				byte b = (byte) "0123456789ABCDEF".indexOf(c);// 返回指定的十六进制字符
				return b;
			}
			/**
			 * 把字节数组转换成16进制字符串
			 * 
			 * @param byte[] bArray
			 * @return String
			 */
			public final String bytesToHexString(byte[] bArray) {
				StringBuffer sb = new StringBuffer(bArray.length);
				String sTemp;
				for (int i = 0; i < bArray.length; i++) {
					sTemp = Integer.toHexString(0xFF & bArray[i]);// 按位与后把每个字节转化成十六进制字符串
					if (sTemp.length() < 2) // 如果转换后十六进制数是一个，那么末尾追加0的字符串形式
						sb.append(0);
					sb.append(sTemp.toUpperCase());// 把十六进制字符串转换成大写
				}
				return sb.toString();
			}
		  public static String get_TEA_Key()
			{
				String result="";byte c;int i=0;
				Random rm=new Random();
		        while(i<16)
				{
					c=(byte)rm.nextInt(128);
				if((c>='A'&&c<='Z')||c>='0'&&c<='9'||c>='a'&&c<='z')
				{
					result+=(char)c;
					i++;
				}
				}
				return result;
			}

          public String getKeyPath()
          {
        	return  keyPath;
          }
}
