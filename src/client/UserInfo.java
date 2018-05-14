package client;

import java.io.Serializable;

public class UserInfo implements Serializable{

	String name;
	int HP=100,num;
	int power,angle;	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void UpPower () {
		this.power++;
		System.out.println("power =" + power );
	}

	public void DownPower(){
		this.power--;
		System.out.println("power =" + power );
	}

	public void UpAngle(){
		this.angle++;
		System.out.println("angle =" + angle );
	}

	public void DownAngle(){
		this.angle--;
		System.out.println("angle =" + angle);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		this.HP = hP;
	}
	public void MinusHP() {
		this.HP -= 10;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
}