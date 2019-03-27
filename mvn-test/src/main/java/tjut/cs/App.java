package tjut.cs;

import java.util.Locale;

import com.github.javafaker.Faker;

public class App {
	static 	Faker faker =new Faker(new Locale("zh-CN"));
	
	public static void main(String[] args) {
		Student s1 = new Student(1,faker.name().fullName(),faker.address().fullAddress());
		System.out.println(s1);
	}

}
