//�ӿ����ͣ��������ߴ������Žӿڣ�֧�ַ�����֤����š�����֪ͨ���ŵȡ�
// �˻�ע�᣺��ͨ���õ�ַ��ͨ�˻�http://user.ihuyi.com/register.html
// ע�����
//��1�������ڼ䣬��ʹ����ϵͳĬ�ϵĶ������ݣ�������֤���ǣ������������벻Ҫ����֤��й¶�������ˡ�
//��2����ʹ�� APIID �� APIKEY�����ýӿڣ����ڻ�Ա���Ļ�ȡ��
//��3���ô���������뻥�����߶��Žӿڲο�ʹ�ã��ͻ��ɸ���ʵ����Ҫ���б�д��

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import util.StringUtil;

public class sendsms {

	private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";

	public static void main(String [] args) {

		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

		int mobile_code = (int)((Math.random()*9+1)*100000);

		String content = new String("������֤���ǣ�" + mobile_code + "���벻Ҫ����֤��й¶�������ˡ�");

		NameValuePair[] data = {//�ύ����
				new NameValuePair("account", "C11182867"), //�鿴�û��� ��¼�û�����->��֤��֪ͨ����>��Ʒ����->API�ӿ���Ϣ->APIID
				new NameValuePair("password", "3dce5433444bfd9407fd1bc19c294aba "), //�鿴���� ��¼�û�����->��֤��֪ͨ����>��Ʒ����->API�ӿ���Ϣ->APIKEY
				//new NameValuePair("password", util.StringUtil.MD5Encode("����")),
				new NameValuePair("mobile", "13533209487"),
				new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult =method.getResponseBodyAsString();

			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			if("2".equals(code)){
				System.out.println("�����ύ�ɹ�");
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
