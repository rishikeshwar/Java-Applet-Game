package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JLabel;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private static Robot robot;
	private Heliboy hb, hb2;
	Thread thread = new Thread();
	public static int score = 0;
	public static int health = 100;
	public static int h = 0;
	public static int burn = 10;
	private Font font = new Font(null, Font.BOLD, 30);
	private Font deadFont = new Font(null, Font.BOLD, 100);
	private static Tile t;
	public static boolean game = true;
	public static long gamecontroler = 0L;
	public Rectangle heartR = new Rectangle(0, 0, 0, 0);
	public Rectangle crownR = new Rectangle(0, 0, 0, 0);
	public Rectangle crystalR = new Rectangle(0, 0, 0, 0);
	public Rectangle robotR = new Rectangle(0, 0, 0, 0);
	public Rectangle fireR = new Rectangle(0, 0, 0, 0);
	public Rectangle shootR = new Rectangle(0, 0, 0, 0);
	public Rectangle tileR = new Rectangle(0, 0, 0, 0);
	public Rectangle wildMuttR = new Rectangle(0, 0, 0, 0);
	public Rectangle keysR = new Rectangle(0, 0, 0, 0);
	public Rectangle crocR = new Rectangle(0, 0, 0, 0);
	public int run = 0;
	public static int crownMovePaint = 0, wildMuttMovePaint = 0;
	public static boolean crownStartPaint = false, wildMuttStartPaint = false;
	public static int tileMoveXPaint = 0, tileMoveYPaint = 0;
	public static int crownMoveUpdate = 0;
	public static boolean crownStartUpdate = false;
	public static int tileMoveXUpdate = 0, tileMoveYUpdate = 0;
	public boolean characterDirection = true;
	private boolean jumped = false;
	public long l = 0L;
	public int r = 0, wildMuttMoveSpeed = 0, crocMoveSpeed = 2,
			crocMoveBody = 0, wildMuttAttackSequence = 0, crocMovePaint = 0;
	public int wildMuttMoveExecute = 0;
	public static boolean shoot = false, crocMoveDirection = false;
	public boolean wildMuttMoveBool = false;
	public int wildMuttAttacked = 0, lives = 5, keysNumber = 0,
			backgroundChangeNumber;

	private Image image, currentSprite, character, character2, character3,
			characterDown, characterJumped, background, newYorkCity,
			superImage, backgrounds[] = new Image[5];

	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt, heart, cornerleft, cornerright, crown,
			fire, heart1, heart2, heart3, heart4, crystal, heliboy, scenery,
			pistol, characterJumpLeftImage, characterJumpRightImage,
			characterRightImage, characterRight1Image, characterRight2Image,
			characterRight3Image, characterRight4Image, characterRight5Image,
			characterRight6Image, characterRight7Image, characterRight8Image,
			characterLeftImage, characterLeft1Image, characterLeft2Image,
			characterLeft3Image, characterLeft4Image, characterLeft5Image,
			characterLeft6Image, characterLeft7Image, characterAttackImage,
			characterShoot1Image, wildMuttRightImage1, wildMuttRightImage2,
			wildMuttRightImage3, wildMuttRightImage4, wildMuttLeftImage1,
			wildMuttLeftImage2, wildMuttLeftImage3, wildMuttLeftImage4,
			crocRightImage1, crocRightImage2, crocRightImage3, crocRightImage4,
			crocRightImage5, crocRightImage6, crocLeftImage1, crocLeftImage2,
			crocLeftImage3, crocLeftImage4, crocLeftImage5, crocLeftImage6,
			wildMuttAttackRight1, yellowTile, characterAttackJumpLeft1,
			characterAttackJumpLeft2, characterAttackJumpLeft3,
			characterAttackJumpLeft4, characterAttackJumpLeft5,
			characterAttackJumpLeft6, characterAttackJumpRight1,
			characterAttackJumpRight2, characterAttackJumpRight3,
			characterAttackJumpRight4, characterAttackJumpRight5,
			characterAttackJumpRight6, newTileMetal, keys, crocLeftAnim,
			crocAttackRight, crocRightAnim, crocAttackLeft,
			characterBurnDamage;

	private Graphics second;
	private URL base;
	private static Background bg1, bg2;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	@Override
	public void init() {

		setSize(1250, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups

		superImage = getImage(base, "data/lives.png");
		character = getImage(base, "data/characterRight1.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		keys = getImage(base, "data/keys.gif");

		characterRightImage = getImage(base, "data/characterRight.png");
		characterRight1Image = getImage(base, "data/characterRight1.png");
		characterRight2Image = getImage(base, "data/characterRight2.png");
		characterRight3Image = getImage(base, "data/characterRight3.png");
		characterRight4Image = getImage(base, "data/characterRight4.png");
		characterRight5Image = getImage(base, "data/characterRight5.png");
		characterRight6Image = getImage(base, "data/characterRight6.png");
		characterRight7Image = getImage(base, "data/characterRight7.png");

		characterLeftImage = getImage(base, "data/characterLeft.png");
		characterLeft1Image = getImage(base, "data/characterLeft1.png");
		characterLeft2Image = getImage(base, "data/characterLeft2.png");
		characterLeft3Image = getImage(base, "data/characterLeft3.png");
		characterLeft4Image = getImage(base, "data/characterLeft4.png");
		characterLeft5Image = getImage(base, "data/characterLeft5.png");
		characterLeft6Image = getImage(base, "data/characterLeft6.png");
		characterLeft7Image = getImage(base, "data/characterLeft7.png");
		characterAttackImage = getImage(base, "data/characterjumpleft2.png");

		characterAttackJumpLeft1 = getImage(base, "data/characterjumpleft1.png");
		characterAttackJumpLeft2 = getImage(base, "data/characterjumpleft2.png");
		characterAttackJumpLeft3 = getImage(base, "data/characterjumpleft3.png");
		characterAttackJumpLeft4 = getImage(base, "data/characterjumpleft4.png");
		characterAttackJumpLeft5 = getImage(base, "data/characterjumpleft5.png");
		characterAttackJumpLeft6 = getImage(base, "data/characterjumpleft6.png");

		characterBurnDamage = getImage(base, "data/characterburn.png");

		characterAttackJumpRight1 = getImage(base,
				"data/characterjumpright1.png");
		characterAttackJumpRight2 = getImage(base,
				"data/characterjumpright2.png");
		characterAttackJumpRight3 = getImage(base,
				"data/characterjumpright3.png");
		characterAttackJumpRight4 = getImage(base,
				"data/characterjumpright4.png");
		characterAttackJumpRight5 = getImage(base,
				"data/characterjumpright5.png");
		characterAttackJumpRight6 = getImage(base,
				"data/characterjumpright6.png");

		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");

		characterJumpLeftImage = getImage(base, "data/characterJumpLeft.png");
		characterJumpRightImage = getImage(base, "data/characterJumpRight.png");

		wildMuttRightImage1 = getImage(base, "data/wildmuttright1.png");
		wildMuttRightImage2 = getImage(base, "data/wildmuttright2.png");
		wildMuttRightImage3 = getImage(base, "data/wildmuttright3.png");
		wildMuttRightImage4 = getImage(base, "data/wildmuttright4.png");
		wildMuttLeftImage1 = getImage(base, "data/wildmuttleft1.png");
		wildMuttLeftImage2 = getImage(base, "data/wildmuttleft2.png");
		wildMuttLeftImage3 = getImage(base, "data/wildmuttleft3.png");
		wildMuttLeftImage4 = getImage(base, "data/wildmuttleft4.png");
		wildMuttAttackRight1 = getImage(base, "data/wildmuttattackright1.png");

		crocLeftAnim = getImage(base, "data/crocanimleft.gif");
		crocRightAnim = getImage(base, "data/crocanimright.gif");
		crocAttackRight = getImage(base, "data/crocattackright.gif");
		crocAttackLeft = getImage(base, "data/crocattackleft.gif");

		background = getImage(base, "data/background.jpg");
		backgrounds[0] = getImage(base, "data/background.jpg");
		backgrounds[1] = getImage(base, "data/background2.jpg");
		backgrounds[2] = getImage(base, "data/background3.jpg");
		backgrounds[3] = getImage(base, "data/background4.jpg");
		backgrounds[4] = getImage(base, "data/background5.jpg");

		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");

		yellowTile = getImage(base, "data/newtile1.png");
		newTileMetal = getImage(base, "data/metalbox.PNG");

		heart = getImage(base, "data/heart.png");
		heart1 = getImage(base, "data/heart1.png");
		heart2 = getImage(base, "data/heart2.png");
		heart3 = getImage(base, "data/heart3.png");
		heart4 = getImage(base, "data/heart4.png");

		// scenery = background;
		cornerleft = getImage(base, "data/cornerleft.png");
		cornerright = getImage(base, "data/cornerright.png");

		crown = getImage(base, "data/crown.png");
		fire = getImage(base, "data/fire.gif");
		crystal = getImage(base, "data/crystal.png");

		heliboy = getImage(base, "data/heliboy.png");
		pistol = getImage(base, "data/pistol.png");
		currentSprite = character;

		characterShoot1Image = getImage(base, "data/shoot1.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2786, 0);

		hb = new Heliboy(800, 697);
		hb2 = new Heliboy(400, 697);
		robot = new Robot();

		// Initialize Tiles
		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {

			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 20; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, ch);
					tilearray.add(t);
				}

			}
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {

		hb.update();
		hb2.update();

		while (game == true) {

			if (robot.getCenterY() >= 5000) {
				game = false;
			}

			robot.update();
			r++;
			if (r == 29)
				r = 1;

			if (robot.isJumped() == true && wildMuttAttacked == 0) {
				// currentSprite = characterJumpRightImage;
				if (characterDirection == true) {
					currentSprite = characterJumpRightImage;
				} else if (characterDirection == false) {

					currentSprite = characterJumpLeftImage;
				}
			}
			if (robot.isJumped() == false && robot.isDucked() == false) {

				if (characterDirection == true && wildMuttAttacked == 0) {

					if (r == 4 && robot.getSpeedX() > 0) {
						currentSprite = characterRight1Image;
					} else if (r == 8 && robot.getSpeedX() > 0) {
						currentSprite = characterRight2Image;
					} else if (r == 12 && robot.getSpeedX() > 0) {
						currentSprite = characterRight3Image;
					} else if (r == 16 && robot.getSpeedX() > 0) {
						currentSprite = characterRight4Image;
					} else if (r == 20 && robot.getSpeedX() > 0) {
						currentSprite = characterRight5Image;
					} else if (r == 24 && robot.getSpeedX() > 0) {
						currentSprite = characterRight6Image;
					} else if (r == 28 && robot.getSpeedX() > 0) {
						currentSprite = characterRight7Image;
					}

					if (robot.getSpeedX() == 0 && wildMuttAttacked == 0) {
						currentSprite = characterRightImage;
					}

				} else if (characterDirection == false && wildMuttAttacked == 0) {

					if (r == 4 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft1Image;
					} else if (r == 8 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft2Image;
					} else if (r == 12 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft3Image;
					} else if (r == 16 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft4Image;
					} else if (r == 20 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft5Image;
					} else if (r == 24 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft6Image;
					} else if (r == 28 && robot.getSpeedX() < 0) {
						currentSprite = characterLeft7Image;
					}

					if (robot.getSpeedX() == 0 && wildMuttAttacked == 0) {
						currentSprite = characterLeftImage;
					}
				}

			}

			if (wildMuttAttacked != 0) {

				if (wildMuttAttacked == 1)
					robot.setCenterX(robot.getCenterX() - 3);
				else if (wildMuttAttacked == 2)
					robot.setCenterX(robot.getCenterX() + 3);

				if (wildMuttAttackSequence >= 20)
					robot.setSpeedY(-3);
				else
					robot.setSpeedY(3);

				if (wildMuttAttackSequence == 40) {

					if (wildMuttAttacked == 1)
						currentSprite = characterAttackJumpLeft1;
					else
						currentSprite = characterAttackJumpRight1;
				} else if (wildMuttAttackSequence == 34) {

					if (wildMuttAttacked == 1)
						currentSprite = characterAttackJumpLeft2;
					else
						currentSprite = characterAttackJumpRight2;
				} else if (wildMuttAttackSequence == 28) {

					if (wildMuttAttacked == 1)
						currentSprite = characterAttackJumpLeft3;
					else
						currentSprite = characterAttackJumpRight3;
				} else if (wildMuttAttackSequence == 22) {

					if (wildMuttAttacked == 1)
						currentSprite = characterAttackJumpLeft4;
					else
						currentSprite = characterAttackJumpRight4;
				} else if (wildMuttAttackSequence == 16) {

					if (wildMuttAttacked == 1)
						currentSprite = characterAttackJumpLeft5;
					else
						currentSprite = characterAttackJumpRight5;
				} else if (wildMuttAttackSequence == 10) {

					if (wildMuttAttacked == 1)
						currentSprite = characterAttackJumpLeft6;
					else
						currentSprite = characterAttackJumpRight6;
				} else if (wildMuttAttackSequence == 1) {
					if (wildMuttAttacked == 1)
						currentSprite = characterRightImage;
					else
						currentSprite = characterLeftImage;
				}

				wildMuttAttackSequence--;
				if (wildMuttAttackSequence == 0)
					wildMuttAttacked = 0;
			}

			if (shoot == true) {
				currentSprite = characterAttackImage;
			}
			ArrayList projectiles = robot.getProjectiles();

			for (int i = 0; i < projectiles.size(); i++) {

				Projectile p = (Projectile) projectiles.get(i);

				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}

			updateTiles();
			bg1.update();
			bg2.update();

			hb.update();
			hb2.update();

			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {

		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		g.drawImage(scenery, 0, 0, this);

		paintTiles(g);

		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 65, this);

		// g.drawImage(heliboy, hb.getCenterX() - 48, hb.getCenterY() - 48,
		// this);
		// g.drawImage(heliboy, hb2.getCenterX() - 48, hb2.getCenterY() - 48,
		// this);

		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("SCORE ", 1000, 30);
		g.drawString(Integer.toString(score), 1150, 30);
		g.drawString("HEALTH ", 600, 30);

		// g.drawRect(robot.rect.x, robot.rect.y, robot.rect.height,
		// robot.rect.width);
		/*
		 * g.drawRect(robot.rect2.x, robot.rect2.y, robot.rect2.height,
		 * robot.rect2.width); g.drawRect(robot.rect3.x, robot.rect3.y,
		 * robot.rect3.height, robot.rect3.width); g.drawRect(robot.rect4.x,
		 * robot.rect4.y, robot.rect4.height, robot.rect4.width);
		 * g.drawRect(robot.rect2.x, robot.rect2.y, 20, 20);
		 */
		if (health <= 0) {
			lives--;
			health = 100;
		}
		g.drawString(Integer.toString(health), 750, 30);
		// g.drawString(Integer.toString(robot.getCenterX()), 400, 100);
		// g.drawString(Integer.toString(robot.getCenterY()), 500, 100);
		g.drawImage(superImage, 20, 60, this);
		g.drawString("==", 110, 120);
		g.drawString(Integer.toString(lives), 160, 120);
		g.drawImage(keys, 20, 160, this);
		g.drawString("==", 110, 220);
		g.drawString(Integer.toString(keysNumber), 160, 220);
		/*
		 * g.drawRect(robot.getCenterX() - 25, robot.getCenterY() - 63, 50,
		 * 126);
		 */

		ArrayList projectiles = robot.getProjectiles();
		for (int j = 0; j < projectiles.size(); j++) {
			Projectile p = (Projectile) projectiles.get(j);
			// g.setColor(Color.RED);
			shootR.setRect(p.getX(), p.getY(), 10, 20);
			g.drawImage(characterShoot1Image, p.getX(), p.getY(), this);
		}
		if (lives <= 0)
			game = false;

		if (game == false) {

			g.setFont(deadFont);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1250, 850);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 500, 425);
			g.drawString("YOUR SCORE ", 50, 600);
			g.drawString(Integer.toString(score), 800, 600);

		}
	}

	private void updateTiles() {

		h += 1;
		if (h == 46)
			h = 1;

		wildMuttMoveSpeed += 5;

		if (wildMuttMoveSpeed >= 400) {
			wildMuttMoveSpeed = 0;
			wildMuttMoveExecute = 0;
		}

		robotR.setRect(robot.getCenterX() - 25, robot.getCenterY() - 63, 50,
				126);

		if (crownStartUpdate == false)
			crownMoveUpdate += 2;

		if (crownMoveUpdate == 100)
			crownStartUpdate = true;

		if (crownStartUpdate == true)
			crownMoveUpdate -= 2;

		if (crownMoveUpdate == 0)
			crownStartUpdate = false;

		if (crocMoveDirection == false) {
			crocMoveBody += crocMoveSpeed;
			// t.setTileImage(crocLeftAnim);
			crocMoveSpeed = 2;
		}
		if (crocMoveBody == 200) {
			crocMoveDirection = true;
			crocMoveSpeed = -2;
		}
		if (crocMoveDirection == true) {
			crocMoveBody += crocMoveSpeed;
			// t.setTileImage(crocRightAnim);
			crocMoveSpeed = -2;
		}
		if (crocMoveBody == 0) {
			crocMoveDirection = false;
			crocMoveSpeed = 2;
		}

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
			// System.out.println(" tileArray " + tilearray.size());
			if (crocR.intersects(robot.rect2) && t.type == 'c') {

			}

			boolean nouse = false;
			if (t.type == '&')
				tileMoveXUpdate = crownMovePaint;

			if (t.type == '1') {

				heartR.setRect(t.tileX, t.tileY, 40, 40);

			} else if (t.type == '&') {

				crownR.setRect(t.tileX - tileMoveXUpdate, t.tileY, 40, 40);

			} else if (t.type == '#') {

				fireR.setRect(t.tileX, t.tileY, 120, 128);

			} else if (t.type == '^') {

				crystalR.setRect(t.tileX + 10, t.tileY, 80, 80);

			} else if (t.type == 'w') {

				wildMuttR.setRect(t.getTileX() - wildMuttMovePaint,
						t.getTileY(), 132, 82);
			} else if (t.type == 'k') {
				keysR.setRect(t.tileX, t.tileY, 80, 80);
			} else if (t.type == 'c') {

				crocR.setRect(t.getTileX() - crocMoveBody, t.getTileY(), 180,
						170);
				// if(run == 39)

			}

			if ((h == 5 || h == 45) && t.type == '1') {
				t.setTileImage(heart);
			} else if ((h == 10 || h == 40) && t.type == '1') {
				t.setTileImage(heart1);

			} else if ((h == 15 || h == 35) && t.type == '1') {
				t.setTileImage(heart2);

			} else if ((h == 20 || h == 30) && t.type == '1') {
				t.setTileImage(heart3);
			} else if (h == 25 && t.type == '1') {
				t.setTileImage(heart4);
			}
			// --------------------------WILDMUTT
			// MOVEMENTS--------------------------------------
			if (wildMuttMoveSpeed == 10 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed;
				t.setTileImage(wildMuttRightImage1);
			} else if (wildMuttMoveSpeed == 60 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed;
				t.setTileImage(wildMuttRightImage2);
			} else if (wildMuttMoveSpeed == 110 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed;
				t.setTileImage(wildMuttRightImage3);
			} else if (wildMuttMoveSpeed == 160 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed;
				t.setTileImage(wildMuttRightImage4);
			} else if (wildMuttMoveSpeed == 210 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed - 50;
				t.setTileImage(wildMuttLeftImage1);
			} else if (wildMuttMoveSpeed == 260 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed - 150;
				t.setTileImage(wildMuttLeftImage2);
			} else if (wildMuttMoveSpeed == 310 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed - 250;
				t.setTileImage(wildMuttLeftImage3);
			} else if (wildMuttMoveSpeed == 360 && t.type == 'w') {
				wildMuttMovePaint = wildMuttMoveSpeed - 350;
				t.setTileImage(wildMuttLeftImage4);
			}
			// ----------------------------------------------------------------------------------

			// --------------------------CROCODILE
			// MOVEMENTS-------------------------------------
			if (t.type == 'c') {

				if (crocR.intersects(robot.rect2)) {

					if (crocMoveSpeed < 0)
						t.setTileImage(crocAttackRight);
					else
						t.setTileImage(crocAttackLeft);
					crocMoveSpeed = 0;

				} else {

					if (crocMoveDirection == false) {

						t.setTileImage(crocLeftAnim);

					}

					if (crocMoveDirection == true) {

						t.setTileImage(crocRightAnim);

					}

				}
			}

			if (heartR.intersects(robotR) && t.type == '1') {

				score += 10;
				tilearray.remove(i);
				System.out.println("HEART");

			} else if (keysR.intersects(robotR) && t.type == 'k') {
				keysNumber++;
				tilearray.remove(i);
				System.out.println("KEYS");

			} else if (t.type == '&' && crownR.intersects(robot.rect2)) {
				score += 50;
				tilearray.remove(i);
				System.out.println("CROWN");

			} else if (crystalR.intersects(robotR) && t.type == '^') {
				score += 100;
				tilearray.remove(i);
				System.out.println("CRYSTAL");
			}

			if (fireR.intersects(robot.rect2) && t.type == '#') {

				if (burn == 10)
					burn = -10;
				else if (burn == -10)
					burn = 10;

				if (robot.getSpeedX() == 0 && robot.getSpeedY() == 0) {
					currentSprite = characterBurnDamage;
				}

				robot.setCenterX(robot.getCenterX() + burn);
				health -= 1;

			}
			/*
			 * if(wildMuttAttacked == true){ robot.setCenterX(robot.getCenterX()
			 * - 1); wildMuttAttackSequence--; if(wildMuttAttackSequence == 0)
			 * wildMuttAttacked = false; }
			 */
			if (wildMuttR.intersects(robot.rect2) && t.type == 'w') {
				if (wildMuttAttacked == 0)
					health -= 20;
				// wildMuttAttacked = true;
				robot.setSpeedX(0);
				robot.setSpeedY(0);
				wildMuttAttackSequence = 40;
				if (wildMuttR.x + 10 > robot.rect2.x) {
					// System.out.println("1");
					wildMuttAttacked = 1;
					// robot.setCenterX(robot.getCenterX() - 100);
					t.setTileImage(wildMuttAttackRight1);

				} else if (wildMuttR.x < robot.rect2.x) {

					wildMuttAttacked = 2;
					// robot.setCenterX(robot.getCenterX() + 100);
					t.setTileImage(wildMuttAttackRight1);
					// System.out.println("2");
				}
			}

		}
	}

	private void paintTiles(Graphics g) {

		if (crownStartPaint == false) // CROWN MOVEMENT
			crownMovePaint += 2;

		if (crownMovePaint == 100)
			crownStartPaint = true;

		if (crownStartPaint == true)
			crownMovePaint -= 2;

		if (crownMovePaint == 0)
			crownStartPaint = false;

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			// System.out.println(" " + t.getTileX());
			// System.out.println("paint " + l++);
			tileMoveXPaint = 0;
			tileMoveYPaint = 0;
			if (t.type == '&')
				tileMoveXPaint = crownMovePaint;

			if (t.type == 'w')
				tileMoveXPaint = wildMuttMovePaint;

			if (t.type == 'c')
				tileMoveXPaint = crocMoveBody;

			/*
			 * if (t.type == '1') { g.drawRect(t.tileX, t.tileY -
			 * tileMoveYPaint, 40, 40); } else if (t.type == '&') {
			 * //g.drawRect(t.getTileX() - crownMovePaint, t.tileY, 40, 40); }
			 * else if (t.type == '#') { g.drawRect(t.tileX, t.tileY, 80, 100);
			 * } else if (t.type == '^') { g.drawRect(t.tileX + 10, t.tileY, 80,
			 * 80); } else if (t.type == 'w') {
			 * 
			 * g.drawRect(t.getTileX() - wildMuttMovePaint, t.getTileY(), 132,
			 * 82);
			 * 
			 * 
			 * } else if (t.type == 'c') { g.drawRect(t.getTileX() -
			 * crocMovePaint, t.getTileY(), 150, 120); }
			 */
			// if (t.tileX >= -50 && t.tileX <= 1300) {
			// g.drawRect(crownR.x, crownR.y, 40, 40);
			// g.drawImage(superImage, 100, 100, this);

			g.drawImage(t.getTileImage(), t.getTileX() - tileMoveXPaint,
					t.getTileY(), this);
			// }

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (wildMuttAttacked == 0) {
				jumped = true;
				robot.jump();
			}
			break;

		case KeyEvent.VK_DOWN:
			if (wildMuttAttacked == 0) {
				currentSprite = characterDown;
				if (robot.isJumped() == false) {
					robot.setDucked(true);
					robot.setSpeedX(0);
				}
			}
			break;

		case KeyEvent.VK_LEFT:
			if (wildMuttAttacked == 0) {
				// currentSprite = characterLeftImage;
				characterDirection = false;
				robot.moveLeft();
				robot.setMovingLeft(true);
			}
			break;

		case KeyEvent.VK_RIGHT:
			if (wildMuttAttacked == 0) {
				// currentSprite = characterRightImage;
				characterDirection = true;
				robot.moveRight();

				robot.setMovingRight(true);
			}
			break;

		case KeyEvent.VK_SPACE:
			break;

		case KeyEvent.VK_CONTROL:
			if (wildMuttAttacked == 0) {
				if (robot.isDucked() == false && robot.isJumped() == false) {
					robot.shoot();
					shoot = true;
				}
			}
			break;

		case KeyEvent.VK_B: {
			backgroundChangeNumber++;
			if (backgroundChangeNumber >= 5)
				backgroundChangeNumber = 0;
			background = backgrounds[backgroundChangeNumber];
		}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			jumped = false;
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = character;
			robot.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			// currentSprite = characterLeftImage;
			robot.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			// currentSprite = characterRightImage;
			robot.stopRight();
			break;

		case KeyEvent.VK_SPACE:

			break;

		case KeyEvent.VK_CONTROL:
			shoot = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Robot getRobot() {
		return robot;
	}

	public static Tile getT() {
		return t;
	}

	public static void setT(Tile t) {
		StartingClass.t = t;
	}

	public static void setRobot(Robot robot) {
		StartingClass.robot = robot;
	}

}