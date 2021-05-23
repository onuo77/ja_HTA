package day1;

public class PhoneApp {
	
	public static void main(String[] args) {
		
		//Phone 객체 생성
		Phone phone = new Phone();
		phone.setTel("010-1111-2222");
		phone.call();
		
		//FeaturePhone 객체 생성
		FeaturePhone featurePhone = new FeaturePhone();
		featurePhone.setTel("010-3333-3333");			//Phone객체로부터 물려받은 속성
		featurePhone.setPixel(7000000);					//FeaturePhone의 고유한 속성
		featurePhone.call();							//phone객체로부터 물려받은 기능
		featurePhone.takePicture();						//FeaturePhone의 고유한 속성
		
		//SmartPhone 객체 생성
		SmartPhone smartPhone = new SmartPhone();
		smartPhone.setTel("010-5555-5555");				//Phone객체로부터 물려받은 속성값 변경
		smartPhone.call();								//Phone객체로부터 물려받은 기능
		smartPhone.setIp("192.168.10.2");				//SmartPhone객체의 고유한 속성값 변경
		smartPhone.setEmailAddress("hong@gmail.com");	//SmartPhone객체의 고유한 속성값 변경
		smartPhone.web();								//SmartPhone객체의 고유한 기능
		smartPhone.mail();								//SmartPhone객체의 고유한 기능
	}
}
