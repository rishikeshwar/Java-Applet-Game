package kiloboltgame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	int tileX;
	int tileY;
	private int speedX;
	char type;
	public Image tileImage;
	public int h = 0;

	private Robot robot = StartingClass.getRobot();
	private Background bg = StartingClass.getBg1();

	private Rectangle r;

	public Tile(int x, int y, char typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rectangle();

		if (type == '5') {
			tileImage = StartingClass.tiledirt;
		} else if (type == '8') {
			tileImage = StartingClass.tilegrassTop;
		} else if (type == '4') {
			tileImage = StartingClass.tilegrassLeft;
		} else if (type == '6') {
			tileImage = StartingClass.tilegrassRight;
		} else if (type == '1') {
			tileImage = StartingClass.heart;
		} else if (type == '&') {
			tileImage = StartingClass.crown;
		} else if (type == '#') {
			tileImage = StartingClass.fire;
		} else if (type == '7') {
			tileImage = StartingClass.cornerleft;
		} else if (type == '9') {
			tileImage = StartingClass.cornerright;
		} else if (type == '^') {
			tileImage = StartingClass.crystal;
		} else if (type == '2') {
			tileImage = StartingClass.tilegrassBot;
		} else if (type == 'w') {
			tileImage = StartingClass.wildMuttRightImage1;
		} else if (type == 'c') {
			tileImage = StartingClass.crocLeftAnim;
		} else if (type == '.') {
			tileImage = StartingClass.yellowTile;
		} else if (type == '*') {
			tileImage = StartingClass.newTileMetal;
		} else if (type == 'k') {
			tileImage = StartingClass.keys;
		} else {

			type = '0';
		}

	}

	public void update() {

		speedX = bg.getSpeedX() * 5;
		if (StartingClass.game == false) {
			//
		}
		tileX += speedX;
		r.setBounds(tileX, tileY, 40, 40);

		if (r.intersects(Robot.yellowRed) && type != '0') {
			checkVerticalCollision(Robot.rect, Robot.rect2);
			checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft,
					Robot.footright);
		}

	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(r)
				&& (type == '8' || type == '7' || type == '9' || type == '2'
						|| type == '5' || type == '.' || type == '*')) {
			robot.setSpeedY(3);

		}

		if (rbot.intersects(r)
				&& (type == '8' || type == '7' || type == '9' || type == '5'
						|| type == '.' || type == '*')) {
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 63);

		}
	}

	public void checkSideCollision(Rectangle rleft, Rectangle rright,
			Rectangle leftfoot, Rectangle rightfoot) {
		if (type == '8' || type == '4' || type == '6' || type == '7'
				|| type == '9' || type == '5' || type == '2' || type == '.'
				|| type == '*') {

			if (rleft.intersects(r)) {
				robot.setCenterX(tileX + 102);

				robot.setSpeedX(0);

			} else if (leftfoot.intersects(r)) {
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}

			if (rright.intersects(r)) {
				robot.setCenterX(tileX - 62);

				robot.setSpeedX(0);
			}

			else if (rightfoot.intersects(r)) {
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
		}
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

}