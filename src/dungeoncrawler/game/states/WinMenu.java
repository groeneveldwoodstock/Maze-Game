package dungeoncrawler.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dungeoncrawler.framework.gamestates.GameState;
import dungeoncrawler.framework.gamestates.GameStateManager;
import dungeoncrawler.framework.gui.WindowManager;
import dungeoncrawler.framework.resources.Resources;
import dungeoncrawler.game.world.Tile;

public class WinMenu extends GameState {

	private String[] optionsMenu;
	private static final String START_GAME = "Start Game!";
	private static final String QUIT_GAME = "Quit game";
	private int selected;
		
	public WinMenu(GameStateManager manager) {
		super(manager);
		this.optionsMenu = new String[] {START_GAME, QUIT_GAME};
		this.selected = 0;
	}

	@Override
	protected void loop() {
	}

	@Override
	protected void render(Graphics graphics) {
		
		graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, WindowManager.WIDTH, WindowManager.HEIGHT);
		graphics.setFont(new Font("Araial", Font.PLAIN, 45));
		graphics.setColor(Color.RED);
		graphics.drawString("Maze Adventure", 20, 45);
		graphics.setFont(new Font("Araial", Font.PLAIN, 25));
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu[i], 20, 90 + i * 40);
		}
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Araial", Font.PLAIN, 23));
		graphics.drawString("Instructions", 20, 190);
		graphics.drawString("Use Arrows or WASD to move.", 20, 210);
		graphics.drawString("Use Space or Q to attack.", 20, 230);
		graphics.drawString("Select and Press Enter to start.", 20, 250);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("Araial", Font.PLAIN, 30));
		graphics.drawString("A new maze every time you play!", 20, 300);
		graphics.setFont(new Font("Araial", Font.PLAIN, 45));
		graphics.setColor(Color.ORANGE);
		graphics.drawString("YOU WIN! Congratulations!", 15, 365);
		graphics.drawString("Select Start For A New Maze!", 15, 400);
		graphics.drawImage(Resources.TEXTURES.get(Resources.CHEST), 575, 20, Tile.SIZE*4, Tile.SIZE*4, null);
		
		
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
			if(this.selected > 0) this.selected--;
			break;
		case KeyEvent.VK_DOWN:
			if(this.selected < this.optionsMenu.length-1) this.selected++;
			break;
		case KeyEvent.VK_ENTER:
			switch(this.optionsMenu[selected]) {
			case START_GAME:
				super.gameStateManager.stackState(new PlayingState(gameStateManager));
				break;
			case QUIT_GAME:
				System.exit(0);
				break;
			}
			break;
		}
	}

	@Override
	protected void keyReleased(int keyCode) {}
}

