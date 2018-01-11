package kiloboltgame;

public class Enemy {

	private int maxHealth, currentHeath, power, speedX, centerX, centerY;
	private Background bg = StartingClass.getBg1();
	

	public void update() {
		
		
		speedX = bg.getSpeedX()*5;
		centerX += speedX;
		
		
		
	}

	public void die() {

	}

	public void attack() {

	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHeath() {
		return currentHeath;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentHeath(int currentHeath) {
		this.currentHeath = currentHeath;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

}
